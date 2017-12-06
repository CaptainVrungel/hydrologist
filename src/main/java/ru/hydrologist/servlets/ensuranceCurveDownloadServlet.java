package ru.hydrologist.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;


@WebServlet("/getCurve/*")
public class ensuranceCurveDownloadServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String imageName = request.getParameter("imageId");
            byte[] data = Files.readAllBytes(new File("C:\\temp\\img\\"+imageName).toPath());

            response.setHeader("Content-Type", getServletContext().getMimeType(imageName));
            response.setHeader("Content-Disposition", "inline; filename=\"" + imageName + "\"");

            BufferedInputStream input = null;
            BufferedOutputStream output = null;

            try {
                input = new BufferedInputStream(new ByteArrayInputStream(data)); // Creates buffered input stream.
                output = new BufferedOutputStream(response.getOutputStream());
                byte[] buffer = new byte[8192];
                for (int length = 0; (length = input.read(buffer)) > 0;) {
                    output.write(buffer, 0, length);
                }
            } finally {
                if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
                if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
            }
        }

}
