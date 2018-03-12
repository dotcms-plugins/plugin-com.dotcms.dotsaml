package com.dotcms.plugin.saml.v3.rest.api.v1;

import com.dotmarketing.util.Logger;
import com.dotmarketing.util.UtilMethods;
import com.dotmarketing.util.json.JSONException;
import com.dotmarketing.util.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class IdpJsonTransformer {

    public static JSONObject idpToJson(IdpConfig idpConfig) throws JSONException, IOException {
        JSONObject jo = new JSONObject();

        jo.put("id", idpConfig.getId());
        jo.put("idpName", idpConfig.getIdpName());
        jo.put("enabled", idpConfig.isEnabled());
        jo.put("sPIssuerURL", idpConfig.getsPIssuerURL());
        jo.put("sPEndponintHostname", idpConfig.getsPEndponintHostname());
        jo.put("privateKey", getCanonicalPathIfExists(idpConfig.getPrivateKey()));
        jo.put("publicCert", getCanonicalPathIfExists(idpConfig.getPublicCert()));
        jo.put("idPMetadataFile", getCanonicalPathIfExists(idpConfig.getIdPMetadataFile()));
        jo.put("optionalProperties", idpConfig.getOptionalProperties());

        return jo;
    }

    public static IdpConfig jsonToIdp(JSONObject jsonObject) throws JSONException {
        IdpConfig idpConfig = new IdpConfig();

        idpConfig.setId(jsonObject.getString("id"));
        idpConfig.setIdpName(jsonObject.getString("idpName"));
        idpConfig.setEnabled(jsonObject.getBoolean("enabled"));
        idpConfig.setsPIssuerURL(jsonObject.getString("sPIssuerURL"));
        idpConfig.setsPEndponintHostname(jsonObject.getString("sPEndponintHostname"));
        idpConfig.setPrivateKey(getFileFromCanonicalPath(jsonObject.getString("privateKey")));
        idpConfig.setPublicCert(getFileFromCanonicalPath(jsonObject.getString("publicCert")));
        idpConfig.setIdPMetadataFile(getFileFromCanonicalPath(jsonObject.getString("idPMetadataFile")));
        idpConfig.setOptionalProperties(jsonObject.getString("optionalProperties"));

        return idpConfig;
    }

    private static String getCanonicalPathIfExists(File file) throws IOException{
        String canonicalPath = "";
        if (file != null){
            canonicalPath = file.getCanonicalPath();
        }
        return canonicalPath;
    }

    private static File getFileFromCanonicalPath(String canonicalPath){
        File file = null;

        if (UtilMethods.isSet(canonicalPath)){
            File fileFromPath = new File(canonicalPath);
            if (fileFromPath.exists()){
                file = fileFromPath;
            } else {
                Logger.error(IdpJsonTransformer.class, "File doesn't exists: " + canonicalPath);
            }
        }

        return file;
    }
}
