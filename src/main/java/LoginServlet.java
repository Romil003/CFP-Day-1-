import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user",value = "Romil"),
                @WebInitParam(name = "password", value = "Start@123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //defining regex for username
        String userName = "^[A-Z][a-z]{3,}$";
        //get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if(Pattern.matches(userName , user)) {
            if (userID.equals(user) && password.equals(pwd)) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is incorrect.</font>");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username is invalid.</font>");
            rd.include(request, response);
        }
    }
}
