package phr33ze.google.com.paladins.api;

import android.content.Context;

import phr33ze.google.com.paladins.hirezstudios.Paladins;

/**
 * Created by Des. Android on 05/09/2017.
 */

public class HiRezAPI {

    /**
     * Developer ID (DevId)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String devId;

    /**
     * Authorization Key (AuthKey)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String authKey;

    /**
     * <p>Initialize Hi-Rez API. All stuff will delivered by Hi-Rez employer via E-Mail.</p>
     * <p>Please fill <a href="https://fs12.formsite.com/HiRez/form48/secure_index.html">this form first</a> to using script.</p>
     * <p>After acceptation your request, you can proceed to action.</p>
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     */
    public HiRezAPI(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    /**
     * Paladins API
     * @param platform Platform
     * @return {@link Paladins}
     */
    public Paladins paladins(Paladins.Platform platform, Context context) {
        return new Paladins(platform, devId, authKey, context);
    }

}
