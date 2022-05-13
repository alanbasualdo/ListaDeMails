package com.mycompany.listademails;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author AlanB
 */
public class ListaDeMails {

    static ArrayList<Mail> mails = new ArrayList();

    public static void main(String[] args) {

        int opcion = 0;

        String mail = "", flag = "", fecha = "", flags = "";

        do {

            try {

                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Menú de opciones\n\n"
                        + "1. Insertar mail y su flag\n"
                        + "2. Insertar flags de orden\n"
                        + "3. Ordenar mails por flags\n"
                        + "4. Mostrar los mails\n"
                        + "5. Vaciar lista de mails\n"
                        + "6. Salir\n\n"));

                switch (opcion) {
                    case 1:
                        mail = JOptionPane.showInputDialog(null, "Ingresar mail: \nFormato: MailA");
                        flag = JOptionPane.showInputDialog(null, "Ingresar flag para " + mail + ": \nFormatos:\nA\nA B\nFlags disponibles: A B C");
                        fecha = JOptionPane.showInputDialog(null, "Ingresar fecha de recepción para " + mail + ": \nFormatos: DD/MM/AA");

                        Mail m = new Mail();

                        m.setMail(mail);
                        m.setFlag(flag);
                        m.setFecha(fecha);
                        mails.add(m);
                        break;
                    case 2:
                        if (flags.isEmpty()) {
                            if (!ListaVacia()) {
                                flags = JOptionPane.showInputDialog(null, "Ingresar flags para el orden:  \nFormato:  [!]<FLAG>-(FIFO|LIFO)\nEjemplo:  B-LIFO|!C-FIFO|C-LIFO");
                                JOptionPane.showMessageDialog(null, flags);
                            } else {
                                JOptionPane.showMessageDialog(null, "Debe haber correos para poder ordenarlos.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, flags);
                        }
                        break;
                    case 3:
                        if (!flags.isEmpty()) {
                            if (!mails.isEmpty()) {
                                Ordenar(flags);
                            } else {
                                JOptionPane.showMessageDialog(null, "La lista de mails no se puede ordenar porque está vacía.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay flags.");
                        }
                        break;
                    case 4:
                        MostrarMails();
                        break;
                    case 5:
                        mails.removeAll(mails);
                        if (mails.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La lista de mails está vacía.");
                        }
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } while (opcion != 6);

    }

    public static boolean ListaVacia() {
        return mails.isEmpty();
    }

    public static void MostrarMails() {

        String lista = "";

        for (int i = 0; i < mails.size(); i++) {
            lista += mails.get(i);
        }

        if (ListaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista de mails está vacía.");
        } else {
            JOptionPane.showMessageDialog(null, lista);
        }
    }

    public static int Contador(String cadena, String caracter) {

        int posicion, contador = 0;

        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }

        return contador;
    }

    public static void Ordenar(String flags) {

        String[] parts = flags.split("\\|");
        String parte1 = parts[0];
        String parte2 = parts[1];
        String parte3 = parts[2];

        int totalA = Contador(flags, "A");
        int totalB = Contador(flags, "B");
        int totalC = Contador(flags, "C");

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!11111111111111111111111111111111111111111
        if (totalA == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "A-LIFO";
                } else {
                    parte1 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "B-LIFO";
                } else {
                    parte1 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte1.contains("!")) {
                if (parte1.contains("LIFO")) {
                    parte1 = "C-LIFO";
                } else {
                    parte1 = "C-FIFO";
                }
            }
        }

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222222222222222222222222222222
        if (totalA == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "A-LIFO";
                } else {
                    parte2 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "B-LIFO";
                } else {
                    parte2 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte2.contains("!")) {
                if (parte2.contains("LIFO")) {
                    parte2 = "C-LIFO";
                } else {
                    parte2 = "C-FIFO";
                }
            }
        }

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!33333333333333333333333333333333333333333
        if (totalA == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "A-LIFO";
                } else {
                    parte3 = "A-FIFO";
                }
            }
        } else if (totalB == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "B-LIFO";
                } else {
                    parte3 = "B-FIFO";
                }
            }
        } else if (totalC == 0) {
            if (parte3.contains("!")) {
                if (parte3.contains("LIFO")) {
                    parte3 = "C-LIFO";
                } else {
                    parte3 = "C-FIFO";
                }
            }
        }

        Deque<String> p1 = new LinkedList<>();
        Deque<String> p2 = new LinkedList<>();
        Deque<String> p3 = new LinkedList<>();

        ArrayList<Mail> mailsCopy = new ArrayList(mails);

        int partesPorParte = 1;

        // 111111111111111111111111111111111111111111111111111111111111111111111
        if (partesPorParte == 1) {
            if (parte1.equals("A-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte1.equals("A-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            if (parte1.equals("B-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte1.equals("B-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            if (parte1.equals("C-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p1.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte1.equals("C-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p1.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            partesPorParte++;
        }

        // 222222222222222222222222222222222222222222222222222222222222222222222
        if (partesPorParte == 2) {
            if (parte2.equals("A-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte2.equals("A-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            if (parte2.equals("B-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte2.equals("B-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            if (parte2.equals("C-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p2.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte2.equals("C-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p2.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            partesPorParte++;
        }

        // 333333333333333333333333333333333333333333333333333333333333333333333
        if (partesPorParte == 3) {
            if (parte3.equals("A-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte3.equals("A-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("A")) {
                        p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }

            }
            if (parte3.equals("B-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte3.equals("B-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("B")) {
                        p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
            if (parte3.equals("C-FIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p3.offer(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            } else if (parte3.equals("C-LIFO")) {
                for (int i = 0; i < mailsCopy.size(); i++) {
                    if (mailsCopy.get(i).getFlag().contains("C")) {
                        p3.push(mailsCopy.get(i).getMail() + "   Flags: " + mailsCopy.get(i).getFlag() + "   Fecha de recepción: " + mailsCopy.get(i).getFecha());
                        mailsCopy.remove(i);
                    }
                }
            }
        }

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);

        String resultadoFinal = "";

        int i = 1;

        if (i == 1) {
            for (String v : p1) {
                resultadoFinal += v + "\n";
            }
            i++;
        }
        if (i == 2) {
            for (String v : p2) {
                resultadoFinal += v + "\n";
            }
            i++;
        }
        if (i == 3) {
            for (String v : p3) {
                resultadoFinal += v + "\n";
            }
            i++;
        }

        JOptionPane.showMessageDialog(null, resultadoFinal);

    }
}
