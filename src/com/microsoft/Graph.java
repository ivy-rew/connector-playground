package com.microsoft;

import com.microsoft.auth.AuthProvider;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
/**
 * Graph
 */
public class Graph {

    private static IGraphServiceClient graphClient = null;
    private static AuthProvider authProvider = null;

    public static IGraphServiceClient clientFor(String accessToken) {
        if (graphClient == null) {
            // Create the auth provider
            authProvider = new AuthProvider(accessToken);

            // Create default logger to only log errors
            DefaultLogger logger = new DefaultLogger();
            logger.setLoggingLevel(LoggerLevel.ERROR);

            // Build a Graph client
            graphClient = GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .logger(logger)
                .buildClient();
        }
        return graphClient;
    }
}