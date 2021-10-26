package csci318.task3.bi.service;

/* This class computes a stream of brand quantities
 * and creates state stores for interactive queries.
 */

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplianceStreamProcessing {

    public final static String BRAND_STATE_STORE = "brand-store";
    public final static String EQUIPMENT_STATE_STORE = "equipment-store";
}
