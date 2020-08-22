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
import br.com.helio.enums.StatusUsuarioEnum;
import br.com.helio.model.Usuario;

@WebServlet(name = "inativaUsuarioServlet", urlPatterns = "/inativaUsuario")
public class InativaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -4548490666960270241L;
	
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
		
		//Faz a ativa��o do usu�rio pelo id
		dao.alteraStatusUsuario(StatusUsuarioEnum.INATIVO.getStatusUsuario(), id);
		
		//Busca os dados j� alterados desse usu�rio para preencher a tela.
		usuario = dao.getUserById(id);
		
		out.println("<html>");
		out.println("<head><title>SGUSR - Usu�rio Inativado com Sucesso!</title></head>");
		out.println("<body>");
		out.println("<h3>Usu�rio Inativado com Sucesso!</h3>");
		out.println("<table border=\"1\">");
		out.println("<tr><td>Nome: </td><td>" + usuario.getNome() + "</td></tr>");
		
		out.println("<tr><td>E-mail: </td><td>");
		if (usuario.getEmail() == null)
			out.println("");
		else
			out.println(usuario.getEmail());
		out.println("</td></tr>");
		
		out.println("<tr><td>Data de Nascimento: </td><td>");
		if (usuario.getDataNascimento() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Status: </td><td>" + usuario.getStatus() + "</td></tr>");
		
		out.println("<tr><td>Data de Inclus�o: </td><td>" + new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").
				format(usuario.getDataInclusao().getTime()) + "</td></tr>");
		
		out.println("<tr><td>�ltima Modifica��o: </td><td>");
		if (usuario.getDataModificacao() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").format(usuario.getDataModificacao().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Descri��o da �ltima Modifica��o: </td><td>");
		if (usuario.getMotivoAlteracao() == null)
			out.println("");
		else
			out.println(usuario.getMotivoAlteracao());
		out.println("</td></tr>");
		
		out.println("</table>");
		out.println("</br></br>");
		out.println("<a href=\"listaUsuarios\">Voltar para a Lista de Usu�rios</a>");
		out.println("<a href=\"index.html\">Voltar para a Home</a>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
