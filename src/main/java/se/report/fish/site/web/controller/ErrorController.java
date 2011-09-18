package se.report.fish.site.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import freemarker.template.TemplateException;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error/*", method = RequestMethod.GET)
    public void error(final HttpServletRequest request, final HttpServletResponse response)
	    throws IOException, TemplateException {
	final String profile = System.getProperty("profile");
	response.setContentType("text/html;charset=UTF-8");
	final Writer writer = response.getWriter();
	final InputStream in = this.getClass().getResourceAsStream("/error-" + profile + ".html");
	IOUtils.copy(in, writer, "utf-8");
	writer.flush();
    }

}