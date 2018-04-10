package com.machine.utils.parameters;

public class CommonParameters {
	/**
	 * The action execution was successful. Show result view to the end user.
	 */
	public static final String SUCCESS = "success";

	/**
	 * The action execution was successful but do not show a view. This is useful
	 * for actions that are handling the view in another fashion like redirect.
	 */
	public static final String NONE = "none";

	/**
	 * The action execution was a failure. Show an error view, possibly asking the
	 * user to retry entering data.
	 */
	public static final String ERROR = "error";

	/**
	 * The action execution require more input in order to succeed. This result is
	 * typically used if a form handling action has been executed so as to provide
	 * defaults for a form. The form associated with the handler should be shown to
	 * the end user.
	 * This result is also used if the given input params are invalid, meaning the
	 * user should try providing input again.
	 */
	public static final String INPUT = "input";

	/**
	 * The action could not execute, since the user most was not logged in. The
	 * login view should be shown.
	 */
	public static final String LOGIN = "login";
	
	
	public final static String LINE_SEPRATER = System.getProperty("line.separator");

	public final static String FILE_SEPRATER = System.getProperty("file.separator");
}
