/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network;

import com.news.test.R;
import com.news.test.constants.NetworkConstants;

public class ErrorHandler {


    public static int getErrorMessageByCodes(int status) {

        int error_id;

        switch (status) {
            case NetworkConstants.CODE_REQUEST_TIMEOUT:
                error_id = R.string.err_request_timeout;
                break;
            case NetworkConstants.CODE_BAD_REQUEST:
                error_id = R.string.err_bad_request;
                break;
            default:
                error_id = R.string.err_internal_server;
                break;
        }

        return error_id;
    }


}
