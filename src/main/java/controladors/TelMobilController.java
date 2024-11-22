package controladors;

import java.io.*;
import java.util.List;

import domini.TelMobil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TelMobilController")
public class TelMobilController extends HttpServlet {
	PrintWriter out;

	public void init() {
		TelMobil mobil1 = new TelMobil(1, "Samsung", "Z", 2004, false, 150f);
		TelMobil mobil2 = new TelMobil(2, "Iphone", "ASIX", 2024, false, 200f);
		TelMobil mobil3 = new TelMobil(3, "LG", "DAW", 2005, true, 210.4f);
		TelMobil.afegirMobil(mobil1);
		TelMobil.afegirMobil(mobil2);
		TelMobil.afegirMobil(mobil3);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		response.setContentType("text/html");
		List<TelMobil> list = TelMobil.getList();
		out.println("<html><body><table border='4'><tr><th>ID</th><th>Marca</th><th>Model</th><th>Any</th><th>Reacondicionat</th><th>Preu</th></tr>");

		for (int i = 0 ; i < list.size() ; i++) {
			TelMobil m = list.get(i);
			String reaco = "No";
			out.println("<tr><td>" + m.getId() + "</td>" + "<td>" + m.getMarca() + "</td><td>" + m.getModel() + "</td>");
			if (m.isReacondicionat()) reaco = "Si";
			out.println("<td>" + m.getAny() + "</td>" + "<td>" + reaco + "</td><td>" + m.getPreu() + "€</td></tr>");
		}
		out.println("</table></body></html>");

	}

	protected int checker(String argument) {
		if (argument != null && !argument.isEmpty()) return (0);
		return (1);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		response.setContentType("text/html");

		String ordre = request.getParameter("ordre");
		if (ordre.equals("Afegir")) {
			String id_str = request.getParameter("id");
			String marca = request.getParameter("marca");
			String model = request.getParameter("model");
			String any_str = request.getParameter("any");
			String reacondicionat = request.getParameter("reacondicionat");
			String preu = request.getParameter("preu");

			String faltenDades = "<html><body><h2>Error: Tots els camps son obligatoris.</h2></body></html>";
			if (checker(id_str) == 1 || checker(marca) == 1) {
				out.println(faltenDades);
			} else if (checker(model) == 1 || checker(any_str) == 1) {
				out.println(faltenDades);
			} else if (checker(reacondicionat) == 1 || checker(preu) == 1) {
				out.println(faltenDades);
			}
			else {
				TelMobil mobil = new TelMobil(Integer.parseInt(id_str), marca, model, Integer.parseInt(any_str), reacondicionat.equals("SI") ? true : false, Float.parseFloat(preu));
				TelMobil.afegirMobil(mobil);
				out.println("<html><body><h2>Telèfon mòbil creat amb èxit.</h2></body></html>");
			}
		} 
		else if (ordre.equals("Eliminar"))
		{
			List<TelMobil> list = TelMobil.getList();
			boolean esborrat = false;
			String id_str = request.getParameter("id");
			for (int i = 0 ; i < list.size() ; i++) {
				TelMobil a = list.get(i);
				if (a.getId() == Integer.parseInt(id_str))  {
					esborrat = true;
					list.remove(a);
				}
			}
			if (esborrat)
				out.println("<html><body><h2>El telèfon mòbil amb ID " + id_str + " s'ha eliminat correctament</h2></body></html>");
			else
				out.println("<html><body><h2>No s'ha trobat el telefon amb ID " + id_str + "</h2></body></html>");
		}
	}
}
