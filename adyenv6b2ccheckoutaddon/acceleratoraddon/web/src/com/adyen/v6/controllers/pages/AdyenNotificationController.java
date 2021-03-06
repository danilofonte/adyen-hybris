/*
 *                        ######
 *                        ######
 *  ############    ####( ######  #####. ######  ############   ############
 *  #############  #####( ######  #####. ######  #############  #############
 *         ######  #####( ######  #####. ######  #####  ######  #####  ######
 *  ###### ######  #####( ######  #####. ######  #####  #####   #####  ######
 *  ###### ######  #####( ######  #####. ######  #####          #####  ######
 *  #############  #############  #############  #############  #####  ######
 *   ############   ############  #############   ############  #####  ######
 *                                       ######
 *                                #############
 *                                ############
 *
 *  Adyen Hybris Extension
 *
 *  Copyright (c) 2017 Adyen B.V.
 *  This file is open source and available under the MIT license.
 *  See the LICENSE file for more info.
 */
package com.adyen.v6.controllers.pages;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.adyen.v6.constants.AdyenControllerConstants;
import com.adyen.v6.security.AdyenNotificationAuthenticationProvider;
import com.adyen.v6.service.AdyenNotificationService;

@Controller
@RequestMapping(value = AdyenControllerConstants.NOTIFICATION_PREFIX)
public class AdyenNotificationController {
    private static final Logger LOG = Logger.getLogger(AdyenNotificationController.class);

    @Resource(name = "adyenNotificationAuthenticationProvider")
    private AdyenNotificationAuthenticationProvider adyenNotificationAuthenticationProvider;

    @Resource(name = "adyenNotificationService")
    private AdyenNotificationService adyenNotificationService;

    private static final String RESPONSE_ACCEPTED = "[accepted]";
    private static final String RESPONSE_NOT_ACCEPTED = "[not-accepted]";

    @RequestMapping(value = "/json", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String onReceive(final HttpServletRequest request) {


	LOG.error("start");

        String requestString = null;
        try {
		LOG.error("try");
            //Parse response body by request input stream so that Spring doesn't try to deserialize
            requestString = IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
		LOG.error("catch");
            LOG.error(e);
            return RESPONSE_NOT_ACCEPTED;
        }

        LOG.error("Received Adyen notification:" + requestString);
        if (! adyenNotificationAuthenticationProvider.authenticateBasic(request)) {
		LOG.error("falha na auth");
            throw new AccessDeniedException("Wrong credentials");
        }

        adyenNotificationService.saveNotifications(requestString);

        return RESPONSE_ACCEPTED;
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test() {

	LOG.error("passou aqui");

	return "Working";
    }
}
