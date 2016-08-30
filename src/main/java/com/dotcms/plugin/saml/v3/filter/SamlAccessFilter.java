package com.dotcms.plugin.saml.v3.filter;

import com.dotcms.plugin.saml.v3.*;
import com.dotcms.plugin.saml.v3.config.Configuration;
import com.dotcms.plugin.saml.v3.init.DefaultInitializer;
import com.dotcms.plugin.saml.v3.init.Initializer;
import com.dotcms.repackage.com.google.common.annotations.VisibleForTesting;
import com.dotcms.repackage.org.apache.commons.lang.StringUtils;
import com.dotmarketing.cms.factories.PublicEncryptionFactory;
import com.dotmarketing.cms.login.factories.LoginFactory;
import com.dotmarketing.util.Config;
import com.dotmarketing.util.Logger;
import com.dotmarketing.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.util.InstancePool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

/**
 * Access filter for SAML plugin, it does the autologin and also redirect to the
 * IDP if the user is not logged in.
 * @author jsanca
 */
public class SamlAccessFilter implements Filter {

    private final SamlAuthenticationService samlAuthenticationService;
    private final Initializer initializer;

    public SamlAccessFilter() {

        this(new OpenSamlAuthenticationServiceImpl(),
                InstanceUtil.newInstance(Config.getStringProperty(
                        DotSamlConstants.DOT_SAML_INITIALIZER_CLASS_NAME, null
                ), DefaultInitializer.class));
    }

    @VisibleForTesting
    public SamlAccessFilter(final SamlAuthenticationService samlAuthenticationService,
                            final Initializer initializer) {

        this.samlAuthenticationService = samlAuthenticationService;
        this.initializer               = (null == initializer)?
                new DefaultInitializer():
                initializer;
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        Logger.info(this, "Going to call the Initializer: " + this.initializer);

        if (!this.initializer.isInitializationDone()) {

            this.initializer.init(Collections.EMPTY_MAP);
        } else {

            Logger.info(this, "The initializer was already init: " + this.initializer);
        }
    } // init.

    private boolean checkAccessFilters (String uri, final String [] filterPaths) {

        boolean filter = false;

        if (null != filterPaths) {

            for (String filterPath : filterPaths) {

                filter |= uri.contains(filterPath); //("saml3/metadata/dotcms_metadata.xml")
            }
        }

        return filter;
    } // checkAccessFilters.

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest  request  = (HttpServletRequest) req;
        final HttpSession         session  = request.getSession(false);
        final Configuration configuration  = (Configuration) InstancePool.get(Configuration.class.getName());
        String redirectAfterLogin = null;

        if (!this.checkAccessFilters(request.getRequestURI(), configuration.getAccessFilterArray())) {

            this.autoLogin(request, response, session);

            if (null == session || null == session.getAttribute(WebKeys.CMS_USER)) {

                redirectAfterLogin = request.getRequestURI() +
                        ((null != request.getQueryString())? "?" + request.getQueryString():
                                     StringUtils.EMPTY);

                Logger.warn(this.getClass(),
                        "Doing Saml Login Redirection when request: " +
                                redirectAfterLogin);

                //if we don't have a redirect yet
                if (null != session) {

                    session.setAttribute(WebKeys.REDIRECT_AFTER_LOGIN,
                            redirectAfterLogin);
                }

                this.samlAuthenticationService.authentication(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private void autoLogin (final HttpServletRequest request,
                            final HttpServletResponse response,
                            final HttpSession         session) {

        final User user =
                this.samlAuthenticationService.getUser(request, response);

        if (null != session && null != user) {
            // todo: 3.7 this should be changed to LoginService
            LoginFactory.doCookieLogin(PublicEncryptionFactory.encryptString
                    (user.getUserId()), request, response);
        }
    } // autoLogin.

    @Override
    public void destroy() {

    }
} // E:O:F:SamlAccessFilter.