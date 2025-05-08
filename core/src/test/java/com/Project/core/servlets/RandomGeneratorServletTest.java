package com.Project.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RandomGeneratorServletTest {

    private RandomGeneratorServlet servlet;
    private SlingHttpServletRequest request;
    private SlingHttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws IOException {
        servlet = new RandomGeneratorServlet();
        request = mock(SlingHttpServletRequest.class);
        response = mock(SlingHttpServletResponse.class);

        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void testDoGetWithMissingTypeParameter() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn(null);

        servlet.doGet(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Missing 'type' parameter"));
    }

    @Test
    void testDoGetWithTypeNumber() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn("Number");

        servlet.doGet(request, response);

        String result = responseWriter.toString();
        assertTrue(result.matches("\\d{6}"));
    }

    @Test
    void testDoGetWithTypeLetters() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn("Letters");

        servlet.doGet(request, response);

        String result = responseWriter.toString();
        assertTrue(result.matches("[A-Z]{6}"));
    }

    @Test
    void testDoGetWithTypeRandom() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn("Random");

        servlet.doGet(request, response);

        String result = responseWriter.toString();
        assertTrue(result.matches("\\d{3}[A-Z]{3}"));
    }

    @Test
    void testDoGetWithInvalidType() throws ServletException, IOException {
        when(request.getParameter("type")).thenReturn("Symbols");

        servlet.doGet(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Invalid parameter"));
    }
}
