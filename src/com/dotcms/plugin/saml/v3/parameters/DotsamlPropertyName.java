package com.dotcms.plugin.saml.v3.parameters;

import com.dotcms.plugin.saml.v3.key.BindingType;
import com.dotcms.plugin.saml.v3.key.DotSamlConstants;
import com.dotcms.plugin.saml.v3.meta.DefaultMetaDescriptorServiceImpl;
import com.dotcms.plugin.saml.v3.meta.MetaDescriptorService;

public enum DotsamlPropertyName {

	//@formatter:off
	
	/**
	 * By default we do not filter anything, but if there is some special cases
	 * (url's) you want to avoid the authentication check, add here the values
	 * comma separated.
	 */
	DOT_SAML_ACCESS_FILTER_VALUES( "access.filter.values"),
	
	/**
	 * By default dotCMS uses:
	 * {@link org.opensaml.saml.saml2.core.AuthnContextComparisonTypeEnumeration}.MINIMUM,
	 * but you can set any different you setting the value (non-case sensitive)
	 * For instance: <code>
	 * authn.comparisontype=BETTER
	 * </code> Will use
	 * {@link org.opensaml.saml.saml2.core.AuthnContextComparisonTypeEnumeration}.BETTER
	 * instead of MINIMUN.
	 */
	DOTCMS_SAML_AUTHN_COMPARISON_TYPE( "authn.comparisontype"),
	
	/**
	 * By default dotCMS uses:
	 * {@link org.opensaml.saml.saml2.core.AuthnContext}.PASSWORD_AUTHN_CTX, but
	 * you can override it just adding the context class ref you want.
	 */
	DOTCMS_SAML_AUTHN_CONTEXT_CLASS_REF("authn.context.class.ref"),
	
	/**
	 * By default we use: urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect
	 * {@link BindingType}.REDIRECT But if you want to use a diff mechanism from
	 * the Single Sign On Service (see SingleSignOnService tag on the
	 * idp-metadata) please override it
	 *
	 * @see BindingType
	 */
	DOTCMS_SAML_BINDING_TYPE("bindingtype"),
	
	/**
	 * Optional key to configure the strategy to sync the roles from IDP to
	 * DOTCMS Remove user from all roles, add to roles from IdP & saml_user_role
	 * (if set) DOTCMS_SAML_BUILD_ROLES_ALL_VALUE = " all"; Remove user from all
	 * roles, add to roles from IdP DOTCMS_SAML_BUILD_ROLES_IDP_VALUE = "idp";
	 * Remove user from all roles, add to roles from saml_user_role (if set).
	 * Ignore roles from IdP. DOTCMS_SAML_BUILD_ROLES_STATIC_ONLY_VALUE =
	 * "staticonly; Do not alter existing user roles, add to roles from
	 * saml_user_role (if set). Ignore roles from IdP.
	 * DOTCMS_SAML_BUILD_ROLES_STATIC_ADD_VALUE = "staticadd;
	 * DOTCMS_SAML_BUILD_ROLES_NONE_VALUE Do not alter user roles in any way
	 */
	DOTCMS_SAML_BUILD_ROLES("build.roles"),
	
	/**
	 * By default dotcms use: 1000, but you can override it just adding the new
	 * time you want.
	 */
	DOT_SAML_CLOCK_SKEW("clock.skew"),
	
	/**
	 * By default dotcms use: "mail", but you can override it just adding the
	 * mail attribute name you want. "mail" will be the expected field name from
	 * the Response coming from the OpenSaml post call.
	 */
	DOT_SAML_EMAIL_ATTRIBUTE("attribute.email.name"),
	
	/**
	 * Boolean value to allow to build a dummy email based on the NameID from
	 * the Idp when the email attribute from the IDP is not present. True will
	 * apply the email generation, false will throw 401 error.
	 */
	DOT_SAML_EMAIL_ATTRIBUTE_ALLOW_NULL("attribute.email.allownull"),
	
	/**
	 * By default dotcms use: "givenName", but you can override it just adding
	 * the first name attribute name you want. "givenName" will be the expected
	 * field name from the Response comming from the OpenSaml post call.
	 */
	DOT_SAML_FIRSTNAME_ATTRIBUTE("attribute.firstname.name"),
	
	/**
	 * Key for host field configuration (see
	 * {@link com.dotcms.plugin.saml.v3.key.DotSamlConstants}.DOT_SAML_FIRSTNAME_ATTRIBUTE_NULL_VALUE)
	 * If the first name attribute is null, this value will be set instead
	 */
	DOT_SAML_FIRSTNAME_ATTRIBUTE_NULL_VALUE("attribute.firstname.nullvalue"),
	
	/**
	 * By default the app will try to logout on any site, however you can
	 * override this property per site in order to avoid the plugin to handle
	 * the logout.
	 */
	DOTCMS_SAML_IS_LOGOUT_NEED("islogoutneed"),
	
	/**
	 * By default dotcms use: "sn", but you can override it just adding the last
	 * name attribute name you want. "sn" will be the expected field name from
	 * the Response comming from the OpenSaml post call.
	 */
	DOT_SAML_LASTNAME_ATTRIBUTE("attribute.lastname.name"),
	
	/**
	 * Key for host field configuration (see
	 * {@link com.dotcms.plugin.saml.v3.key.DotSamlConstants}.DOT_SAML_FIRSTNAME_ATTRIBUTE_NULL_VALUE)
	 * If the last name attribute is null, this value will be set instead
	 */
	DOT_SAML_LASTNAME_ATTRIBUTE_NULL_VALUE("attribute.lastname.nullvalue"),
	
	/**
	 * This is the logout service endpoint url, which means the url where to be
	 * redirected when the user gets log out. You can set it for instance to
	 * http://[domain]/c in order to get back to the page.
	 */
	DOT_SAML_LOGOUT_SERVICE_ENDPOINT_URL("logout.service.endpoint.url"),
	
	/**
	 * By default {@link DefaultMetaDescriptorServiceImpl} is what we use to
	 * parse the idp metadata XML file however if you have you own
	 * implementation of {@link MetaDescriptorService} you can override it.
	 */
	DOT_SAML_IDP_METADATA_PARSER_CLASS_NAME("idp.metadata.parser.classname"),
	
	/**
	 * By default dot cms use
	 * {@link DotSamlConstants}.DOT_SAML_IDP_METADATA_PROTOCOL_DEFAULT_VALUE, in
	 * case you need to use a differente feel free to override this property.
	 */
	DOT_SAML_IDP_METADATA_PROTOCOL("idp.metadata.protocol"),
	
	/**
	 * By default true, you can override as a false if your assertions are
	 * returned non-encrypted.
	 */
	DOTCMS_SAML_IS_ASSERTION_ENCRYPTED("isassertion.encrypted"),
	
	/**
	 * By default dotcms use: 2000, but you can override it just adding the new
	 * time you want.
	 */
	DOT_SAML_MESSAGE_LIFE_TIME("message.life.time"),
	
	/**
	 * By default we use the implementation
	 * {@link handler.HttpPostAssertionResolverHandlerImpl} which is in charge
	 * of resolve the assertion using the SOAP artifact resolver based on the
	 * artifact id pass by the request. If you want a different implementation
	 * please override with the class here.
	 */
	DOTCMS_SAML_ASSERTION_RESOLVER_HANDLER_CLASS_NAME("assertion.resolver.handler.classname"),
	
	/**
	 * By default false, you can override as a true if you want to force the
	 * authentication.
	 */
	DOTCMS_SAML_FORCE_AUTHN("force.authn"),
	
	/**
	 * In case you need a custom credentials for the ID Provider (DotCMS)
	 * overrides the implementation class on the configuration properties.
	 */
	DOT_SAML_ID_PROVIDER_CUSTOM_CREDENTIAL_PROVIDER_CLASSNAME("id.provider.custom.credential.provider.classname"),
	
	/**
	 * If you have already set a idp-metadata, this value will be taken from it,
	 * otherwise you have to set it on the dotCMS properties. If the value is
	 * not present will got an exception on runtime. This value is the Redirect
	 * SLO (Logout) url (usually Shibboleth), which is the one to be redirect
	 * when the user does logout on dotCMS.
	 */
	DOTCMS_SAML_IDENTITY_PROVIDER_DESTINATION_SLO_URL("identity.provider.destinationslo.url"),
	
	/**
	 * If you have already set a idp-metadata, this value will be taken from it,
	 * otherwise you have to set it on the dotCMS properties. If the value is
	 * not present will got an exception on runtime. This value is the Redirect
	 * SSO url (usually Shibboleth), which is the one to be redirect when the
	 * user is not logged on dotCMS.
	 */
	DOTCMS_SAML_IDENTITY_PROVIDER_DESTINATION_SSO_URL("identity.provider.destinationsso.url"),
	
	/**
	 * By default we include /c and /admin, if you need to add more into the
	 * saml filter you can include the values comma separated.
	 */
	DOT_SAML_INCLUDE_PATH_VALUES("include.path.values"),
	
	/**
	 * This is an array comma separated, if this array is set. Any role from
	 * SAML that does not match with the list of include roles pattern, will be
	 * filtered.
	 */
	DOTCMS_SAML_INCLUDE_ROLES_PATTERN("include.roles.pattern"),
	
	/**
	 * By default we include "/c/portal/logout,/dotCMS/logout", if you need to
	 * add more into the saml more path you can include the values comma
	 * separated.
	 */
	DOT_SAML_LOGOUT_PATH_VALUES("logout.path.values"),
	
	/**
	 * SAML allows several formats, such as Kerberos, email, Windows Domain
	 * Qualified Name, etc. By default dotcms use:
	 * {@link org.opensaml.saml.saml2.core.NameIDType}.TRANSIENT. See More on
	 * {@link org.opensaml.saml.saml2.core.NameIDType}
	 */
	DOTCMS_SAML_NAME_ID_POLICY_FORMAT("nameidpolicy.format"),
	
	/**
	 * Optional key. Role to be assigned to a logged user besides the default
	 * SAML User
	 */
	DOTCMS_SAML_OPTIONAL_USER_ROLE("role.extra"),
	
	/**
	 * If you want to allow to create an user that does not exists on the IdP,
	 * set this to true, otherwise false. By default it is false, so won't allow
	 * to create the user, however the Idp will be the final responsable to
	 * decided if the user could be or not created.
	 */
	DOTCMS_SAML_POLICY_ALLOW_CREATE("policy.allowcreate"),
	
	/**
	 * Used to get the Saml protocol binding, by default use
	 * {@link DotSamlConstants}.SAML2_ARTIFACT_BINDING_URI
	 */
	DOTCMS_SAML_PROTOCOL_BINDING("protocol.binding"),
	
	/**
	 * Optional Key. By default dotcms do not use any filter, but you can
	 * override it just adding the filter you want. For instance, sometimes LDAP
	 * providers use a prefix for an external roles or so, you can remove this
	 * prefix by setting this prop.
	 */
	DOT_SAML_REMOVE_ROLES_PREFIX("remove.roles.prefix"),
	
	/**
	 * By default dotcms use: "authorisations", but you can override it just
	 * adding the roles attribute name you want. "authorisations" will be the
	 * expected field name from the Response comming from the OpenSaml post
	 * call.
	 */
	DOT_SAML_ROLES_ATTRIBUTE("attribute.roles.name"),
	
	/**
	 * In case you need a custom credentials for the Service Provider (DotCMS)
	 * overrides the implementation class on the configuration properties.
	 */
	DOT_SAML_SERVICE_PROVIDER_CUSTOM_CREDENTIAL_PROVIDER_CLASSNAME("service.provider.custom.credential.provider.classname"),
	
	/**
	 * By default we do not include the encryptor in the metadata, if you want
	 * to include it set this to true.
	 */
	DOTCMS_SAML_USE_ENCRYPTED_DESCRIPTOR("use.encrypted.descriptor"),
	
	/**
	 * By default the system will do the verification of the signature
	 * credentials, if for some reason you want to avoid it feel free to set it
	 * to "false".
	 */
	DOT_SAML_VERIFY_SIGNATURE_CREDENTIALS("verify.signature.credentials"),
	
	/**
	 * By default the system will do the verification of the profile signature,
	 * if for some reason you want to avoid it feel free to set it to "false".
	 */
	DOT_SAML_VERIFY_SIGNATURE_PROFILE("verify.signature.profile"),
	//@formatter:on

	/**
	 * By default any query string included on the endpoints Locations from the IDP metadata will be removed in the moment to redirect to the IDP endpoint.
	 * However you can set this on false in order to keep any query string parameter on the IDP metadata
	 */
	DOTCMS_SAML_CLEAR_LOCATION_QUERY_PARAMS("location.cleanqueryparams");

	private String propertyName;

	DotsamlPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Find the enum constant based on propertyName ;
	 * 
	 * @param propertyName
	 * @return found enum or null if not found.
	 */

	public static DotsamlPropertyName findProperty(String propertyName) {
		for (DotsamlPropertyName dotcmsPropertyName : values()) {
			
			if (dotcmsPropertyName.getPropertyName().equals(propertyName)) {
				return dotcmsPropertyName;
			}
		}
		return null;
	}

}
