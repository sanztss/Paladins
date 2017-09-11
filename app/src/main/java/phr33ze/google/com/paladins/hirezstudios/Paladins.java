package phr33ze.google.com.paladins.hirezstudios;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import phr33ze.google.com.paladins.hirezstudios.instance.Language;
import phr33ze.google.com.paladins.util.StringData;

/**
 * Created by Des. Android on 05/09/2017.
 */

public class Paladins extends HiRezGames{

    public enum Platform {
        /**
         * Paladins PC API
         */
        PC("http://api.paladins.com/paladinsapi.svc"),
        /**
         * Paladins XBOX One API
         */
        XBOX("http://api.xbox.paladins.com/paladinsapi.svc"),
        /**
         * Paladins PlayStation 4 API
         */
        PS4("http://api.ps4.paladins.com/paladinsapi.svc");

        /**
         * API Url
         */
        final String url;

        /**
         * End points for API
         * @param url URL for API
         */
        Platform(String url) {
            this.url = url;
        }

        /**
         * Getting URL
         * @return API URL
         */
        public String getUrl() { return url; }
    }

    public enum Queue {
        Custom_K_StoneKeep(423),
        LIVE_Casual(424),
        LIVE_Practice_Siege(425),
        Challenge_Match(426),
        LIVE_Payload_Practice(427),
        LIVE_Competitive(428),
        WIP_Test_MOTD_PvE(429),
        Custom_F_TimberMill(430),
        Custom_F_Dock(431),
        Custom_I_Igloo(432),
        Custom_T_Isle(433),
        Shooting_Range(434),
        PerfCaptureMap(435),
        Tencent_Alpha_Siege_Queue_Coop(436),
        LIVE_Payload(437),
        Custom_T_Temple(438),
        Custom_I_Mine(439),
        Custom_T_Beach(440),
        Custom_TP(441),
        Custom_FP(442),
        Custom_IP(443),
        Tutorial(444),
        LIVE_Test_Maps(445),
        zOLD_PvE_Seismic_Smash(446),
        zOLD_PvE_Coven(447),
        zOLD_PvE_Volcanic_Wrath(448),
        zOLD_PvE_Archery_Contest(449),
        zOLD_PvE_Cops_and_Robbers(450),
        zOLD_PvE_Kill_it_with_Fire(451),
        LIVE_Onslaught(452),
        LIVE_Onslaught_Practice(453),
        Custom_I_Junction_Onslaught(454),
        Custom_T_Court_Onslaught(455),
        Tencent_Alpha_Payload(456),
        Tencent_Alpha_Survival_Queue_Coop(457),
        Custom_B_Brightmarsh(458),
        Multi_Queue(999);

        private int id;

        private static Map<Integer, Queue> map = new HashMap<Integer, Queue>();

        static {
            for(Queue queue : Queue.values()) {
                map.put(queue.id, queue);
            }
        }

        Queue(final int id) {
            this.id = id;
        }

        public int getId() { return id; }

        public static Queue valueOf(int id) {
            return map.get(id);
        }
    }


    /**
     * Smite API
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param platform Platform in {@link Paladins.Platform}
     */
    public Paladins(Paladins.Platform platform, String devId, String authKey, Context context) {
        super(platform, devId, authKey, context);
    }

    /**
     * Getting Champion loadouts by player
     * @param playerId This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns deck loadouts per Champion.
     */
    public StringData getplayerloadouts(String playerId) {
        return get("getplayerloadouts", playerId);
    }

    /**
     * Getting Champion loadouts by player
     * @param playerId This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns deck loadouts per Champion.
     */
    public StringData getplayerloadouts(int playerId) {
        return get("getplayerloadouts", String.valueOf(playerId));
    }

    /**
     * Getting Champion ranks by player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Rank and Worshippers value for each Champion a player has played.
     */
    public StringData getChampionRanks(String username) {
        return get("getchampionranks", username);
    }

    /**
     * Getting Champions list.
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all Champions and their various attributes.
     */
    public StringData getChampions(Language language) {
        return get("getchampions", String.valueOf(language.getId()));
    }

    /**
     * Getting Champions list. (Default {@link Language#English})
     * @return Returns all Champions and their various attributes.
     */
    public StringData getChampions() {
        return getChampions(Language.English);
    }

    /**
     * Getting list skins of the Champion
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all available skins for a particular Champion.
     */
    public StringData getChampionSkins(String champId, Language language) {
        return get("getchampionskins", champId, String.valueOf(language.getId()));
    }

    /**
     * Getting list skins of the Champion
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all available skins for a particular Champion.
     */
    public StringData getChampionSkins(int champId, Language language) {
        return getChampionSkins(String.valueOf(champId), language);
    }

    /**
     * Getting list skins of the Champion. (Default {@link Language#English})
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns all available skins for a particular Champion.
     */
    public StringData getChampionSkins(String champId) {
        return getChampionSkins(champId, Language.English);
    }

    /**
     * Getting list skins of the Champion. (Default {@link Language#English})
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns all available skins for a particular Champion.
     */
    public StringData getChampionSkins(int champId) {
        return getChampionSkins(champId, Language.English);
    }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns the Recommended Items for a particular Champion.
     */
    public StringData getChampionRecommendedItems(String champId, Language language) { return get("getchampionecommendeditems", champId, String.valueOf(language.getId())); }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns the Recommended Items for a particular Champion.
     */
    public StringData getChampionRecommendedItems(int champId, Language language) { return getChampionRecommendedItems(String.valueOf(champId), language);}

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns the Recommended Items for a particular Champion.
     */
    public StringData getChampionRecommendedItems(String champId) { return getChampionRecommendedItems(champId, Language.English); }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns the Recommended Items for a particular Champion.
     */
    public StringData getChampionRecommendedItems(int champId) { return getChampionRecommendedItems(champId, Language.English); }


}
