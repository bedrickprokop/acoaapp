package br.com.acoaapp.constants;

public interface MessageConstants {

    //tags
    String TAG_NAVIGATION_DRAWER_FRAGMENT = "NAVIGATION_DRAWER_FRAGMENT";
    String TAG_GENERAL_STATISTICS_FRAGMENT = "GENERAL_STATISTICS_FRAGMENT";
    String TAG_CONSUMPTION_HISTORY_FRAGMENT = "HISTORY_CONSUMPTION_FRAGMENT";
    String TAG_SUSTAINTABILITY_FRAGMENT = "SUSTAINTABILITY_FRAGMENT";
    String TAG_MY_ACCOUNT_FRAGMENT = "MY_ACCOUNT_FRAGMENT";

    //extra intents
    String INTENT_KEY_MESSAGE = "message";
    String INTENT_KEY_COLLABORATOR = "collaborator";
    String INTENT_KEY_COLLABORATORCODE = "collaboratorCode";
    String INTENT_KEY_COLLABORATORLIST = "collaboratorList";
    String INTENT_KEY_RELOAD_COLLABORATORS = "reloadCollaborators";

    String KEY_STATISTICS_ENTITY = "statisticsEntity";

    //request codes
    int REQUESTCODE_ADDCOLLABORATOR = 100;
    int REQUESTCODE_EDITCOLLABORATOR = 200;
}
