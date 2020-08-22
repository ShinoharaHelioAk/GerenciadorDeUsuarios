package br.com.helio.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helio.dao.UsuarioDAO;
import br.com.helio.model.Usuario;

@WebServlet(name = "carregaUsuarioParaAlteracaoServlet", urlPatterns = "/carregaUsuarioParaAlteracao")
public class CarregaUsuarioParaAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = -5336590577465217288L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idRequestParam = req.getParameter("id");
		//System.out.println(idRequestParam);
		Long id = Long.parseLong(idRequestParam);
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		PrintWriter out = resp.getWriter();
		//out.println("Testando o Servlet " + getServletName() + ".");
		//out.println("Passando o ID " + id + ".");
		
		out.println("<html>");
		out.println("<head><title>SGUSR - Alteração de Dados do Usuário</title></head>");
		out.println("<body>");
		out.println("<h3>Página de Alteração de Usuários</h3>");
		
		//Buscar na base os dados do usuário usando o id
		usuario = dao.getUserById(id);
		
		
		//Popular os campos da Servlet
		out.println("<form action=\"alteraUsuario\">");
		out.println("<input type=\"hidden\" name=\"id\" value=\"" + usuario.getId() + "\">");
		out.println("Nome: <input required=\"true\" type=\"text\" name=\"nome\" value=\"" + usuario.getNome() + "\"></br>");
		
		out.println("E-mail: <input type=\"email\" name=\"email\" value=\"" + usuario.getEmail() + "\"></br>");
		
		if (usuario.getDataNascimento() == null)
			out.println("Data de Nascimento: <input type=\"date\" name=\"dataNascimento\" value=\"" + 
					usuario.getDataNascimento() + "\"></br>");
		else
			out.println("Data de Nascimento: <input type=\"date\" name=\"dataNascimento\" value=\"" + 
					new SimpleDateFormat("yyyy-MM-dd").format(usuario.getDataNascimento().getTime()) + "\"></br>");
		
		out.println("<input type=\"submit\" value=\"Gravar\">");
		
		out.println("</br></br>");
		out.println("<a href=\"listaUsuarios\">Voltar para a Lista de Usuários</a>");
		out.println("<a href=\"index.html\">Voltar para a Home</a>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
