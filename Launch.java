package intro.collection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class Launch {
	static List<Cliente> lista;

	public static void main(String[] args) {
		lista = new ArrayList<Cliente>();
		Launch l = new Launch();

		while (true) {
			try {
				int option = Integer.parseInt(JOptionPane.showInputDialog(
						"Digite a opção:\n1.inserir no inicio\n2.inserir no final\n3.ver lista\n4.consultar\n5.deletar\n6.limpar lista\n7.edita\n8.salvar lista\n0.sair"));
				switch (option) {
				case 1:
					l.insert(0);
					continue;
				case 2:
					l.insert(lista.size());
					continue;
				case 3:
					JOptionPane.showMessageDialog(null, l.printAll());
					continue;
				case 4:
					l.query();
					continue;
				case 5:
					l.delete();
					continue;
				case 6:
					if (!lista.isEmpty()) {
						lista.clear();
						JOptionPane.showMessageDialog(null, "Lista limpada com êxito!");
						continue;
					} else {
						JOptionPane.showMessageDialog(null, "A lista está vazia!");
						continue;
					}
				case 7:
					l.edit();
					continue;
				case 8:
					l.write(l.printAll());
					continue;
				case 0:
					System.exit(0);
					break;

				default:
					JOptionPane.showMessageDialog(null, "Digite uma opção válida!");
					continue;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Digite uma posição valida!");
				continue;
			}
		}
	}

	public void insert(int position) {
		Cliente c = new Cliente();
		String s = JOptionPane.showInputDialog("Digite o nome:");
		if (s == null)
			return;
		if (lista.contains(s)) {
			JOptionPane.showMessageDialog(null, "Usuario já cadastrado!", "err", JOptionPane.ERROR_MESSAGE);
			return;
		}
		c.setNome(s);
		s = JOptionPane.showInputDialog("Digite o email:");
		if (s == null)
			return;
		c.setEmail(s);
		lista.add(position, c);
	}

	public String printAll() {
		if (!lista.isEmpty()) {
			Iterator<Cliente> i = lista.iterator();
			int p = 1;
			String str = "LISTA DE USUÁRIOS | TOTAL: "+ lista.size()+"\n\n";
			while (i.hasNext()) {
				str += p + ". " + i.next() + "\n";
				p++;
			}
			return str;
		} else {
			return "A lista está vazia!";
		}
	}

	public void query() {
		try {
			if (!lista.isEmpty()) {
				int p = Integer.parseInt(JOptionPane.showInputDialog("Digite a posição:"));
				JOptionPane.showMessageDialog(null, lista.get(p - 1));

			} else {
				JOptionPane.showMessageDialog(null, "A lista está vazia!");
				return;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite uma posição valida!");
		}
	}

	public void delete() {
		try {
			if (!lista.isEmpty()) {
				int p = Integer.parseInt(JOptionPane.showInputDialog("Digite a posição:"));
				String cache = lista.get(p - 1).toString();
				lista.remove(p - 1);
				JOptionPane.showMessageDialog(null, "Usuário '" + cache + "' deletado com sucesso!");

			} else {
				JOptionPane.showMessageDialog(null, "A lista está vazia!");
				return;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite uma posição válida!");
		}
	}

	public void edit() {
		Cliente c = new Cliente();
		try {
			if (!lista.isEmpty()) {
				int p = Integer.parseInt(JOptionPane.showInputDialog("Digite a posição:"));
				if (p < 1 || p > lista.size()) {
					JOptionPane.showMessageDialog(null, "Digite uma posição válida!");
				}
				String cache = lista.get(p - 1).toString();
				String s = JOptionPane.showInputDialog("Digite o nome:");
				if (s == null)
					return;
				c.setNome(s);
				s = JOptionPane.showInputDialog("Digite o email:");
				if (s == null)
					s = "vazio";
				c.setEmail(s);
				lista.set(p - 1, c);
				JOptionPane.showMessageDialog(null, "Usuário '" + cache + "' alterado com sucesso!");

			} else {
				JOptionPane.showMessageDialog(null, "A lista está vazia!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Digite uma posição válida!");
		}
	}

	public void write(String data) {
		if (!lista.isEmpty()) {
			try {
				File caminho = new File(JOptionPane.showInputDialog("Digite onde salvar:"));
				FileWriter fWriter = new FileWriter(caminho);
				BufferedWriter bWriter = new BufferedWriter(fWriter);
				bWriter.write(data);
				bWriter.close();
				JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso em: " + caminho.toString());

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo!", "erro",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possível salvar o arquivo, pois, a lista está vazia!", "erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
