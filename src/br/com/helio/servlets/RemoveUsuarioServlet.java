package br.com.helio.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helio.dao.UsuarioDAO;

@WebServlet(name = "removeUsuarioServlet", urlPatterns = "/removeUsuario")
public class RemoveUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 5344826657962443590L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idRequestParam = req.getParameter("id");
		//System.out.println(idRequestParam);
		Long id = Long.parseLong(idRequestParam);
		
		UsuarioDAO dao = new UsuarioDAO();
		
		
		PrintWriter out = resp.getWriter();
		//out.println("Testando o Servlet " + getServletName() + ".");
		//out.println("Passando o ID " + id + ".");

		dao.removeUser(id);
		
		out.println("<html>");
		out.println("<head><title>SGUSR - Usuário Removido da Base de Dados</title></head>");
		out.println("<body>");
		out.println("<h3>SGUSR - Usuário Removido da Base de Dados</h3>");
		out.println("</br></br>");
		out.println("<form action=\"cadastraUsuarios.html\">\r\n"
				+ "		<input type=\"submit\" value=\"Cadastrar Novo Usuário\">\r\n");
		out.println("<a href=\"listaUsuarios\">Voltar para a Lista de Usuários</a>");
		out.println("<a href=\"index.html\">Voltar para a Home</a>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}