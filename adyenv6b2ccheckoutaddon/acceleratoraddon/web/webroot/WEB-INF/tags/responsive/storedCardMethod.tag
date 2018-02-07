<%--
  ~                        ######
  ~                        ######
  ~  ############    ####( ######  #####. ######  ############   ############
  ~  #############  #####( ######  #####. ######  #############  #############
  ~         ######  #####( ######  #####. ######  #####  ######  #####  ######
  ~  ###### ######  #####( ######  #####. ######  #####  #####   #####  ######
  ~  ###### ######  #####( ######  #####. ######  #####          #####  ######
  ~  #############  #############  #############  #############  #####  ######
  ~   ############   ############  #############   ############  #####  ######
  ~                                       ######
  ~                                #############
  ~                                ############
  ~
  ~  Adyen Hybris Extension
  ~
  ~  Copyright (c) 2017 Adyen B.V.
  ~  This file is open source and available under the MIT license.
  ~  See the LICENSE file for more info.
  --%>
<%@ attribute name="variant" required="true" type="java.lang.String" %>
<%@ attribute name="cardReference" required="true" type="java.lang.String" %>
<%@ attribute name="cardNumber" required="true" type="java.lang.String" %>
<%@ attribute name="expiryMonth" required="true" type="java.lang.String" %>
<%@ attribute name="expiryYear" required="true" type="java.lang.String" %>

<%@ taglib prefix="adyen" tagdir="/WEB-INF/tags/addons/adyenv6b2ccheckoutaddon/responsive" %>

<div class="chckt-pm chckt-pm-${variant}_r@1 js-chckt-pm js-chckt-pm__pm-holder" data-additional-required="" data-pm="${variant}_r@1">
    <div class="chckt-pm__header js-chckt-pm__header">
        <adyen:methodSelector name="adyen_oneclick_${cardReference}"/>
        <span class="chckt-pm__name js-chckt-pm__name">**** **** **** ${cardNumber}</span>
        <span class="chckt-pm__image">
            <img width="40" src="https://checkoutshopper-live.adyen.com/checkoutshopper/img/pm/${variant}@2x.png" alt="">
            <span class="chckt-pm__image-border"></span>
        </span>
    </div>

    <div class="chckt-pm__details js-chckt-pm__details" id="dd_method_adyen_oneclick_${cardReference}">
        <div class="chckt-form chckt-form--max-width">
            <div class="chckt-pm__recurring-details">
                <input type="hidden" name="txvariant" value="${variant}_r@1"/>
                <label class="chckt-form-label chckt-form-label--exp-date">
                    <span class="chckt-form-label__text">Expiry Date:</span>
                    <span class="chckt-input-field chckt-input-field--recurring" data-cse="encryptedSecurityCode">${expiryMonth}/${expiryYear}</span>
                </label>
                <label class="chckt-form-label chckt-form-label--cvc">
                    <span class="chckt-form-label__text js-chckt-cvc-field-label">CVV:</span>
                    <span class="chckt-input-field chckt-input-field--cvc js-chckt-hosted-input-field" data-hosted-id="hostedSecurityCodeField" data-cse="encryptedSecurityCode-${cardReference}" data-optional=""></span>
                    <span class="chckt-form-label__error-text">Invalid CVC</span>
                </label>
            </div>
        </div>
    </div>
</div>
