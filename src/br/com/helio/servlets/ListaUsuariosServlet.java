package br.com.helio.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helio.dao.UsuarioDAO;
import br.com.helio.enums.StatusUsuarioEnum;
import br.com.helio.model.Usuario;

@WebServlet(name = "listaUsuariosServlet", urlPatterns = "/listaUsuarios")
public class ListaUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 874357486005141408L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDAO dao = new UsuarioDAO();
		// Usuario usuario = new Usuario();
		List<Usuario> usuarios = new ArrayList<Usuario>();

		usuarios = dao.getListUsers();
		//log("Tamanho da lista: " + usuarios.size());

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>GUSR - Lista de Usu�rios Cadastrados</title></head>");
		out.println("<body>");
		out.println("<h3>Lista de Usu�rios Cadastrados</h3>");
		out.println("<table border=\"1\">");

		out.println("<tr>");
		/*
		 * out.println("<td>"); out.println("ID"); out.println("</td>");
		 */
		out.println("<td>");
		out.println("Nome");
		out.println("</td>");

		out.println("<td>");
		out.println("E-mail");
		out.println("</td>");

		out.println("<td>");
		out.println("Data de Nascimento");
		out.println("</td>");

		out.println("<td>");
		out.println("Status");
		out.println("</td>");

		out.println("<td>");
		out.println("Data de Inclus�o");
		out.println("</td>");

		out.println("<td>");
		out.println("�ltima Modifica��o");
		out.println("</td>");

		out.println("<td>");
		out.println("Descri��o da �ltima Modifica��o");
		out.println("</td>");

		out.println("<td>");
		out.println("");
		out.println("</td>");

		out.println("<td>");
		out.println("");
		out.println("</td>");

		out.println("<td>");
		out.println("");
		out.println("</td>");
		
		/*
		 * out.println("<td>");
		 * out.println("");
		 * out.println("</td>");
		*/

		out.println("</tr>");
		
		usuarios.forEach((usuario) -> {
			out.println("<tr>");
			/*
			 * out.println("<td>"); out.println(usuario.getId()); out.println("</td>");
			 */
			out.println("<td>");
			out.println(usuario.getNome());
			out.println("</td>");

			out.println("<td>");
			if (usuario.getEmail() == null)
				out.println("");
			else
				out.println(usuario.getEmail());
			out.println("</td>");

			out.println("<td>");
			if (usuario.getDataNascimento() == null)
				out.println("");
			else
				out.println(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento().getTime()));
			out.println("</td>");

			out.println("<td>");
			out.println(usuario.getStatus());
			out.println("</td>");

			out.println("<td>");
			out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").format(usuario.getDataInclusao().getTime()));
			out.println("</td>");

			out.println("<td>");
			if (usuario.getDataModificacao() == null)
				out.println("");
			else
				out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").format(usuario.getDataModificacao().getTime()));
			out.println("</td>");

			out.println("<td>");
			if (usuario.getMotivoAlteracao() == null)
				out.println("");
			else
				out.println(usuario.getMotivoAlteracao());
			out.println("</td>");

			if (usuario.getStatus().equals(StatusUsuarioEnum.BLOQUEADO.getStatusUsuario())) {
				out.println("<td>");
				/*
				 * out.println("<form action=\"ativaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Ativar Usu�rio\">");
				 * out.println("</form>");
				 * out.println("</td>");
				 */
				out.println("<a href=\"ativaUsuario?id=" + usuario.getId() + "\">Ativar Usu�rio</a>");
				out.println("<td>");
				/*
				 * out.println("<form action=\"inativaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Inativar Usu�rio\">");
				 * out.println("</form>");
				 */
				out.println("<a href=\"inativaUsuario?id=" + usuario.getId() + "\">Inativar Usu�rio</a>");
				out.println("</td>");
			} else if (usuario.getStatus().equals(StatusUsuarioEnum.ATIVO.getStatusUsuario())) {
				out.println("<td>");
				/*
				 * out.println("<form action=\"bloqueiaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Bloquear Usu�rio\">");
				 * out.println("</form>");
				 */
				out.println("<a href=\"bloqueiaUsuario?id=" + usuario.getId() + "\">Bloquear Usu�rio</a>");
				out.println("</td>");
				
				out.println("<td>");
				/*
				 * out.println("<form action=\"inativaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Inativar Usu�rio\">");
				 * out.println("</form>");
				 */
				out.println("<a href=\"inativaUsuario?id=" + usuario.getId() + "\">Inativar Usu�rio</a>");
				out.println("</td>");
			} else {
				out.println("<td>");
				/*
				 * out.println("<form action=\"ativaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Ativar Usu�rio\">");
				 * out.println("</form>");
				 * out.println("</td>");
				 */
				out.println("<a href=\"ativaUsuario?id=" + usuario.getId() + "\">Ativar Usu�rio</a>");
				out.println("</td>");
				
				out.println("<td>");
				/*
				 * out.println("<form action=\"bloqueiaUsuario\">");
				 * out.println("<input type=\"submit\" value=\"Bloquear Usu�rio\">");
				 * out.println("</form>");
				 */
				out.println("<a href=\"bloqueiaUsuario?id=" + usuario.getId() + "\">Bloquear Usu�rio</a>");
				out.println("</td>");
			}

			out.println("<td>");
			/*
			 * out.println("<form action=\"alteraUsuario\">");
			 * out.println("<input type=\"submit\" value=\"Alterar Usu�rio\">");
			 * out.println("</form>");
			 */
			out.println("<a href=\"carregaUsuarioParaAlteracao?id=" + usuario.getId() + "\">Alterar Usu�rio</a>");
			out.println("</td>");

			//out.println("<td>");
			/*
			 * out.println("<form action=\"removeUsuario\">");
			 * out.println("<input type=\"submit\" value=\"Remover Usu�rio\">");
			 * out.println("</form>");
			 */
			//out.println("<a href=\"detalhaUsuario?id=" + usuario.getId() + "\">Remover Usu�rio</a>");
			//out.println("</td>");

			out.println("</tr>");
		});

		out.println("</table>");
		out.println("</br></br>");
		out.println("<form action=\"cadastraUsuarios.html\">\r\n"
				+ "		<input type=\"submit\" value=\"Cadastrar Novo Usu�rio\">\r\n");
		out.println("<a href=\"index.html\">Voltar para a Home</a>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
