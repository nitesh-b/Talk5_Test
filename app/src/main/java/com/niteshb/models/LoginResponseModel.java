package com.niteshb.models;

import java.io.Serializable;
import java.util.Date;

public class LoginResponseModel implements Serializable {

    private String access_token, full_name, company_name, company_logo, preferred_language_code, unique_code, company_unique_code, token_type, refresh_token;
    private Date expiry_date;
    private int expire_in_minutes;
    private Boolean offline_access, allow_photo_attachment, allow_talk_text, allow_sos, allow_multiple_audits;

    public LoginResponseModel() {

    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getPreferred_language_code() {
        return preferred_language_code;
    }

    public void setPreferred_language_code(String preferred_language_code) {
        this.preferred_language_code = preferred_language_code;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public String getCompany_unique_code() {
        return company_unique_code;
    }

    public void setCompany_unique_code(String company_unique_code) {
        this.company_unique_code = company_unique_code;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getExpire_in_minutes() {
        return expire_in_minutes;
    }

    public void setExpire_in_minutes(int expire_in_minutes) {
        this.expire_in_minutes = expire_in_minutes;
    }

    public Boolean getOffline_access() {
        return offline_access;
    }

    public void setOffline_access(Boolean offline_access) {
        this.offline_access = offline_access;
    }

    public Boolean getAllow_photo_attachment() {
        return allow_photo_attachment;
    }

    public void setAllow_photo_attachment(Boolean allow_photo_attachment) {
        this.allow_photo_attachment = allow_photo_attachment;
    }

    public Boolean getAllow_talk_text() {
        return allow_talk_text;
    }

    public void setAllow_talk_text(Boolean allow_talk_text) {
        this.allow_talk_text = allow_talk_text;
    }

    public Boolean getAllow_sos() {
        return allow_sos;
    }

    public void setAllow_sos(Boolean allow_sos) {
        this.allow_sos = allow_sos;
    }

    public Boolean getAllow_multiple_audits() {
        return allow_multiple_audits;
    }

    public void setAllow_multiple_audits(Boolean allow_multiple_audits) {
        this.allow_multiple_audits = allow_multiple_audits;
    }
}
