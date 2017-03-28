package util;

import play.Configuration;

public class Constants {

	public static final String AUTHENTICATED_USER_ID = "sId";
	public static final String AUTHENTICATED_USER_ROLE = "sRole";

	public static final String TEST = Configuration.root().getString("api.key");

}
