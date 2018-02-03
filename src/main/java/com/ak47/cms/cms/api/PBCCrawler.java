package com.ak47.cms.cms.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by wb-cmx239369 on 2017/11/3.
 */

public class PBCCrawler extends Crawler {
    private static final Logger logger = LoggerFactory.getLogger(PBCCrawler.class);
    private static Optional<PBCCrawler> pbcCrawlerOptional = Optional.ofNullable(null);

    private PBCCrawler() {
        super();
    }

    /**
     * 单例获取PBCCrawler
     *
     * @return
     * @throws Exception
     */
    public static PBCCrawler instanceCrawler() {
        if (!pbcCrawlerOptional.isPresent()) {
            pbcCrawlerOptional = Optional.of(new PBCCrawler());
        }
        return pbcCrawlerOptional.get();
    }

}