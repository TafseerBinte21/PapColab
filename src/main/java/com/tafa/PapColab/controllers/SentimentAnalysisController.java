package com.tafa.PapColab.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tafa.PapColab.services.SentimentAnalysisPython;

import py4j.GatewayServer;


import org.springframework.web.bind.annotation.RestController;


@RestController
public class SentimentAnalysisController {
    @PostMapping("/analyzeSentiment")
    public String analyzeSentiment(@RequestBody String text) {
        GatewayServer gatewayServer = new GatewayServer(null); // Initialize the gateway
        gatewayServer.start();

        Object sentimentAnalysisPython = gatewayServer.getPythonServerEntryPoint(null);
        String stat = ((SentimentAnalysisPython) sentimentAnalysisPython).analyzeSentiment(text);

        gatewayServer.shutdown();

        return stat;
    }
}


