package com.tafa.PapColab.services;

import py4j.GatewayServer;



public class SentimentAnalysisPython {
    // Define a Python interpreter instance
    private PythonInterpreter pythonInterpreter;

    public SentimentAnalysisPython() {
        // Initialize the Python interpreter
        pythonInterpreter = new PythonInterpreter();
    }

    public String analyzeSentiment(String text) {
        // Call the Python sentiment analysis script and pass 'text' as an argument
        String script = "from your_sentiment_module import analyze_sentiment\n" +
                        "result = analyze_sentiment('" + text + "')\n" +
                        "result";
        
        PyString result = (PyString) pythonInterpreter.eval(script);

        // Return the sentiment analysis result as a string
        return result.toString();
    }

    public static void main(String[] args) {
        // Create a gateway server for communication
        GatewayServer gatewayServer = new GatewayServer(new SentimentAnalysisPython());

        // Start the server
        gatewayServer.start();
        System.out.println("Python sentiment analysis service is running.");
    }
}

    

