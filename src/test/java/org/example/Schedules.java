package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author George
 * @date 2022/07/18/18:24
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Schedules  {

    @JsonProperty("id")
    protected Long otherId;

    @JsonProperty("account_id")
    protected Long accountId;

    @JsonProperty("customer_id")
    protected Long customerId;

    @JsonProperty("email_customer")
    protected Boolean emailCustomer;

    @JsonProperty("frequency")
    protected String frequency;

    @JsonProperty("name")
    protected String name;

    @JsonProperty("next_run")
    protected LocalDateTime nextRun;

    @JsonProperty("snail_mail")
    protected Boolean snailMail;

    @JsonProperty("charge_mop")
    protected Boolean chargeMop;

    @JsonProperty("subtotal")
    protected Long subtotal;

    @JsonProperty("invoice_unbilled_ticket_charges")
    protected Boolean invoiceUnbilledTicketCharges;

    @JsonProperty("paused")
    protected Boolean paused;

    @JsonProperty("last_invoice_paid")
    protected String lastInvoicePaid;

    @JsonProperty("lines")
    protected JsonNode lines;

}