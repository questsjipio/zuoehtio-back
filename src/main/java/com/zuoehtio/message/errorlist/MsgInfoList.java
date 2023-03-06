package com.zuoehtio.message.errorlist;

public class MsgInfoList {
    public static final MsgInfo ERR_GEN_001 = new MsgInfo("ERR_GEN_001", "Please add at least.");
    public static final MsgInfo ERR_GEN_002 = new MsgInfo("ERR_GEN_002", "This field needs exactly.");
    public static final MsgInfo ERR_GEN_003 = new MsgInfo("ERR_GEN_003", "This field can only have up to.");
    public static final MsgInfo ERR_GEN_004 = new MsgInfo("ERR_GEN_004", "This field is required.");
    public static final MsgInfo ERR_GEN_005 = new MsgInfo("ERR_GEN_005", "At least one of the mandatory fields is blank. Please check.");
    public static final MsgInfo ERR_GEN_006 = new MsgInfo("ERR_GEN_006", "At least one of the fields contains wrong value. Please check.");
    public static final MsgInfo ERR_GEN_007 = new MsgInfo("ERR_GEN_007", "Internal Server Error. Please contact administrator.");
    public static final MsgInfo ERR_GEN_008 = new MsgInfo("ERR_GEN_008", "Value is rejected.");
    public static final MsgInfo ERR_GEN_009 = new MsgInfo("ERR_GEN_009", "Internal Server Error. Please contact administrator.");
    public static final MsgInfo ERR_GEN_010 = new MsgInfo("ERR_GEN_010", "An error occurred. Contact administrator if problem persists.");
    public static final MsgInfo ERR_GEN_012 = new MsgInfo("ERR_GEN_012", "This project does not exist.");
    public static final MsgInfo ERR_GEN_013 = new MsgInfo("ERR_GEN_013", "This project is no longer ongoing. Update request has been rejected.");
    public static final MsgInfo ERR_GEN_014 = new MsgInfo("ERR_GEN_014", "No change to any field has been detected. Update request has been rejected.");
    public static final MsgInfo ERR_NETWORK = new MsgInfo("ERR_NETWORK", "Connection has problem. Please try again later.");
    public static final MsgInfo ERR_RGX_001 = new MsgInfo("ERR_RGX_001", "Please enter alphanumeric characters only.");
    public static final MsgInfo ERR_RGX_002 = new MsgInfo("ERR_RGX_002", "Please enter a proper email address.");
    public static final MsgInfo ERR_RGX_003 = new MsgInfo("ERR_RGX_003", "Name can only contain alphabets, space or hyphens.");
    public static final MsgInfo ERR_RGX_004 = new MsgInfo("ERR_RGX_004", "Please enter a Singapore phone number.");
    public static final MsgInfo SUCCESS_001 = new MsgInfo("SUCCESS_001", "Transaction Successful");

    private MsgInfoList() {
        // Not Empty
    }
}
