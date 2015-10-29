package org.sfaci.damgames.gui;

import com.toedter.calendar.JDateChooser;
import org.sfaci.damgames.base.Compania;
import org.sfaci.damgames.base.Juego;
import org.sfaci.damgames.base.Programador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplicación que gestiona los datos de Juegos, Compañias y Programadores
 *
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Ventana {
    private JPanel panel1;
    private JTabbedPane tpPestanas;
    private JTextField tfNombreJuego;
    private JTextField tfGeneroJuego;
    private JTextField tfSoporteJuego;
    private JTextField tfPrecioJuego;
    private JTextField tfEdadMinimaJuego;
    private JList jlistJuegos;
    private JTextField tfFiltroJuego;
    private JButton btGuardarJuego;
    private JButton btNuevoJuego;
    private JButton btModificarJuego;
    private JButton btEliminarJuego;
    private JButton btCancelarJuego;
    private JTextField tfNombreCompania;
    private JTextField tfDomicilioCompania;
    private JTextField tfPaisCompania;
    private JList jlistJuegosCompania;
    private JList jlistProgramadoresCompania;
    private JButton btNuevoCompania;
    private JButton btGuardarCompania;
    private JButton btModificarCompania;
    private JButton btEliminarCompania;
    private JButton btCancelarCompania;
    private JTextField tfNombreProgramador;
    private JTextField tfApellidosProgramador;
    private JTextField tfEmailProgramador;
    private JTextField tfSalarioProgramador;
    private JList jlistJuegosProgramador;
    private JList jlistCompanias;
    private JTextField tfFiltroProgramador;
    private JButton btNuevoProgramador;
    private JButton btGuardarProgramador;
    private JButton btEliminarProgramador;
    private JButton btModificarProgramador;
    private JButton btCancelarProgramador;
    private JList jlistProgramadores;
    private JButton btAnteriorJuego;
    private JButton btSiguienteJuego;
    private JButton btUltimoJuego;
    private JButton btPrimeroJuego;
    private JButton btPrimeroCompania;
    private JButton btAnteriorCompania;
    private JButton btSiguienteCompania;
    private JButton btUltimoCompania;
    private JButton btPrimeroProgramador;
    private JButton btAnteriorProgramador;
    private JButton btSiguienteProgramador;
    private JButton btUltimoProgramador;
    private JDateChooser dcFechaFundacionCompania;
    private JDateChooser dcFechaPublicacion;
    private JTextField tfFiltroCompania;
    private JLabel lbEstado;
    private JComboProgramador JComboProgramador2;

    private List<Juego> listaJuegos;
    private List<Compania> listaCompanias;
    private List<Programador> listaProgramadores;

    private int posicionJuegos;
    private int posicionCompanias;
    private int posicionProgramadores;

    private DefaultListModel<Juego> modeloListaJuegos;
    private DefaultListModel<Compania> modeloListaCompanias;
    private DefaultListModel<Programador> modeloListaProgramadores;

    private boolean nuevoJuego;
    private boolean nuevoProgramador;
    private boolean nuevaCompania;

    private enum Tipo {
        JUEGO, COMPANIA, PROGRAMADOR
    }

    public Ventana() {

        listaJuegos = new ArrayList<Juego>();
        listaCompanias = new ArrayList<Compania>();
        listaProgramadores = new ArrayList<Programador>();
        posicionJuegos = 0;
        posicionCompanias = 0;
        posicionProgramadores = 0;

        modeloListaJuegos = new DefaultListModel<Juego>();
        jlistJuegos.setModel(modeloListaJuegos);
        modeloListaCompanias = new DefaultListModel<Compania>();
        jlistCompanias.setModel(modeloListaCompanias);
        modeloListaProgramadores = new DefaultListModel<Programador>();
        jlistProgramadores.setModel(modeloListaProgramadores);

        tpPestanas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                cargarPestanaActual();
            }
        });
        btNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevo(Tipo.JUEGO);
            }
        });
        btGuardarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardar(Tipo.JUEGO);
            }
        });
        btModificarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar(Tipo.JUEGO);
            }
        });
        btEliminarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar(Tipo.JUEGO);
            }
        });
        btCancelarJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar(Tipo.JUEGO);
            }
        });
        tfFiltroJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filtrar(Tipo.JUEGO);
            }
        });

        btNuevoCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevo(Tipo.COMPANIA);
            }
        });
        btGuardarCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardar(Tipo.COMPANIA);
            }
        });
        btModificarCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar(Tipo.COMPANIA);
            }
        });
        btEliminarCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar(Tipo.COMPANIA);
            }
        });
        btCancelarCompania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar(Tipo.COMPANIA);
            }
        });
        tfFiltroCompania.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filtrar(Tipo.COMPANIA);
            }
        });

        btNuevoProgramador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nuevo(Tipo.PROGRAMADOR);
            }
        });
        btGuardarProgramador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                guardar(Tipo.PROGRAMADOR);
            }
        });
        btModificarProgramador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar(Tipo.PROGRAMADOR);
            }
        });
        btEliminarProgramador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar(Tipo.PROGRAMADOR);
            }
        });
        btCancelarProgramador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar(Tipo.PROGRAMADOR);
            }
        });
        tfFiltroProgramador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                filtrar(Tipo.PROGRAMADOR);
            }
        });
    }

    private void createUIComponents() {
        JComboProgramador2 = new JComboProgramador<Programador>(listaProgramadores);
    }

    private void cargarPestanaActual() {
        int indice = tpPestanas.getSelectedIndex();

        switch (indice) {
            case 0:
                cargarPestana(Tipo.JUEGO);
                break;
            case 1:
                cargarPestana(Tipo.COMPANIA);
                break;
            case 2:
                cargarPestana(Tipo.PROGRAMADOR);
                break;
            default:
                break;
        }
    }

    private void refrescarCombos(Tipo tipo) {
        switch (tipo) {
            case JUEGO:


                break;
            case COMPANIA:
                if (listaCompanias.size() == 0)
                    return;

                break;
            case PROGRAMADOR:
                break;
        }
    }

    private void cargarPestana(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                if (listaJuegos.size() == 0)
                    return;

                Juego juego = listaJuegos.get(posicionJuegos);

                tfNombreJuego.setText(juego.getNombre());
                tfGeneroJuego.setText(juego.getGenero());
                dcFechaPublicacion.setDate(juego.getFechaPublicacion());
                tfSoporteJuego.setText(juego.getSoporte());
                tfPrecioJuego.setText(String.valueOf(juego.getPrecio()));
                tfEdadMinimaJuego.setText(String.valueOf(juego.getEdadMinima()));

                break;
            case COMPANIA:
                if (listaCompanias.size() == 0)
                    return;

                Compania compania = listaCompanias.get(posicionCompanias);

                break;
            case PROGRAMADOR:
                if (listaProgramadores.size() == 0)
                    return;

                Programador programador = listaProgramadores.get(posicionProgramadores);
                break;
        }

        refrescarlista(tipo);
        refrescarCombos(tipo);
    }

    private void modoEdicion(Tipo tipo, boolean editable) {
        switch (tipo) {
            case JUEGO:
                if (editable) {
                    tfNombreJuego.setText("");
                    tfGeneroJuego.setText("");
                    tfSoporteJuego.setText("");
                    tfPrecioJuego.setText("");
                    dcFechaPublicacion.setDate(null);
                    tfEdadMinimaJuego.setText("");

                }
                tfNombreJuego.setEditable(editable);
                tfGeneroJuego.setEditable(editable);
                dcFechaPublicacion.setEnabled(editable);
                tfSoporteJuego.setEditable(editable);
                tfPrecioJuego.setEditable(editable);
                tfEdadMinimaJuego.setEditable(editable);


                btNuevoJuego.setEnabled(!editable);
                btCancelarJuego.setEnabled(editable);
                btGuardarJuego.setEnabled(editable);
                btModificarJuego.setEnabled(!editable);
                btEliminarJuego.setEnabled(!editable);

                btPrimeroJuego.setEnabled(!editable);
                btUltimoJuego.setEnabled(!editable);
                btAnteriorJuego.setEnabled(!editable);
                btSiguienteJuego.setEnabled(!editable);
                break;
            case COMPANIA:
                if (editable) {
                    tfNombreCompania.setText("");
                    tfDomicilioCompania.setText("");
                    dcFechaFundacionCompania.setDate(null);
                    tfPaisCompania.setText("");
                }

                tfNombreCompania.setEditable(editable);
                tfDomicilioCompania.setEditable(editable);
                dcFechaFundacionCompania.setEnabled(editable);
                tfPaisCompania.setEditable(editable);

                btNuevoCompania.setEnabled(!editable);
                btCancelarCompania.setEnabled(editable);
                btGuardarCompania.setEnabled(editable);
                btModificarCompania.setEnabled(!editable);
                btEliminarCompania.setEnabled(!editable);

                btPrimeroCompania.setEnabled(!editable);
                btUltimoCompania.setEnabled(!editable);
                btAnteriorCompania.setEnabled(!editable);
                btSiguienteCompania.setEnabled(!editable);
                break;
            case PROGRAMADOR:
                if (editable) {
                    tfNombreProgramador.setText("");
                    tfApellidosProgramador.setText("");
                    tfEmailProgramador.setText("");
                    tfSalarioProgramador.setText("");
                }

                tfNombreProgramador.setEditable(editable);
                tfApellidosProgramador.setEditable(editable);
                tfEmailProgramador.setEditable(editable);
                tfSalarioProgramador.setEditable(editable);
                tfSalarioProgramador.setEditable(editable);

                btNuevoProgramador.setEnabled(!editable);
                btCancelarProgramador.setEnabled(editable);
                btGuardarProgramador.setEnabled(editable);
                btModificarProgramador.setEnabled(!editable);
                btEliminarProgramador.setEnabled(!editable);

                btPrimeroProgramador.setEnabled(!editable);
                btUltimoProgramador.setEnabled(!editable);
                btAnteriorProgramador.setEnabled(!editable);
                btSiguienteProgramador.setEnabled(!editable);
                break;
        }
    }

    private void buscar(Tipo tipo, String filtro) {
        switch (tipo) {
            case JUEGO:
                modeloListaJuegos.removeAllElements();
                for (Juego juego : listaJuegos) {
                    if (juego.getNombre().contains(filtro) || juego.getGenero().contains(filtro) || juego.getSoporte().contains(filtro))
                        modeloListaJuegos.addElement(juego);
                }
                break;
            case COMPANIA:
                modeloListaCompanias.removeAllElements();
                for (Compania compania : listaCompanias) {
                    if (compania.getNombre().contains(filtro) || compania.getPais().contains(filtro) || compania.getDomicilio().contains(filtro))
                        modeloListaCompanias.addElement(compania);
                }
                break;
            case PROGRAMADOR:
                modeloListaProgramadores.removeAllElements();
                for (Programador programador : listaProgramadores) {
                    if (programador.getNombre().contains(filtro) || programador.getApellidos().contains(filtro))
                        modeloListaProgramadores.addElement(programador);
                }
                break;
        }
    }

    private void nuevo(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                nuevoJuego = true;
                break;
            case COMPANIA:
                nuevaCompania = true;
                break;
            case PROGRAMADOR:
                nuevoProgramador = true;
                break;
        }

        modoEdicion(tipo, true);
    }

    private void guardar(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                Juego juego = null;

                if (nuevoJuego)
                    juego = new Juego();
                else
                    juego = listaJuegos.get(posicionJuegos);

                juego.setNombre(tfNombreJuego.getText());
                juego.setGenero(tfGeneroJuego.getText());
                juego.setFechaPublicacion(dcFechaPublicacion.getDate());
                juego.setSoporte(tfSoporteJuego.getText());
                juego.setPrecio(Float.parseFloat(tfPrecioJuego.getText()));
                juego.setEdadMinima(Integer.parseInt(tfEdadMinimaJuego.getText()));


                if (nuevoJuego)
                    listaJuegos.add(juego);

                break;
            case COMPANIA:
                Compania compania = null;
                if (nuevaCompania)
                    compania = new Compania();
                else
                    compania = listaCompanias.get(posicionCompanias);

                compania.setNombre(tfNombreCompania.getText());
                compania.setDomicilio(tfDomicilioCompania.getText());
                compania.setFechaFundacion(dcFechaFundacionCompania.getDate());
                compania.setPais(tfPaisCompania.getText());

                if (nuevaCompania)
                    listaCompanias.add(compania);

                break;
            case PROGRAMADOR:
                Programador programador = null;
                if (nuevoProgramador)
                    programador = new Programador();
                else
                    programador = listaProgramadores.get(posicionProgramadores);

                programador.setNombre(tfNombreProgramador.getText());
                programador.setApellidos(tfApellidosProgramador.getText());
                programador.setEmail(tfEmailProgramador.getText());
                programador.setSalario(Float.valueOf(tfSalarioProgramador.getText()));


                if (nuevoProgramador)
                    listaProgramadores.add(programador);

                break;
        }

        modoEdicion(tipo, false);
        refrescarlista(tipo);
    }

    private void cancelar(Tipo tipo) {

        if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Cancelar Edición", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            return;

        cargarPestanaActual();
        modoEdicion(tipo, false);
    }

    private void modificar(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                nuevoJuego = false;
                break;
            case COMPANIA:
                nuevaCompania = false;
                break;
            case PROGRAMADOR:
                nuevoProgramador = false;
                break;
        }

        modoEdicion(tipo, true);
    }

    private void eliminar(Tipo tipo) {

        if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            return;

        switch (tipo) {
            case JUEGO:
                listaJuegos.remove(posicionJuegos);
                if (listaJuegos.size() > 0)
                    if (posicionJuegos == listaJuegos.size())
                        posicionJuegos--;
                break;
            case COMPANIA:
                listaCompanias.remove(posicionCompanias);
                if (listaCompanias.size() > 0)
                    if (posicionCompanias == listaCompanias.size())
                        posicionCompanias--;
                break;
            case PROGRAMADOR:
                listaProgramadores.remove(posicionProgramadores);
                if (listaProgramadores.size() > 0)
                    if (posicionProgramadores == listaProgramadores.size())
                        posicionProgramadores--;
                break;
        }

        cargarPestanaActual();
        lbEstado.setText("Eliminado correctamente");
    }

    private void filtrar(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                if (tfFiltroJuego.getText().equals("")) {
                    refrescarlista(tipo);
                    return;
                }

                if (tfFiltroJuego.getText().length() > 3)
                    modeloListaJuegos.removeAllElements();
                break;
            case COMPANIA:
                if (tfFiltroCompania.getText().equals("")) {
                    refrescarlista(tipo);
                    return;
                }

                if (tfFiltroCompania.getText().length() > 3)
                    modeloListaCompanias.removeAllElements();
                break;
            case PROGRAMADOR:
                break;
        }

        buscar(tipo, tfFiltroJuego.getText());
    }

    private void refrescarlista(Tipo tipo) {
        switch (tipo) {
            case JUEGO:
                modeloListaJuegos.removeAllElements();
                for (Juego juego : listaJuegos)
                    modeloListaJuegos.addElement(juego);
                break;
            case COMPANIA:
                modeloListaCompanias.removeAllElements();
                for (Compania compania : listaCompanias)
                    modeloListaCompanias.addElement(compania);
                break;
            case PROGRAMADOR:
                modeloListaProgramadores.removeAllElements();
                for (Programador programador : listaProgramadores)
                    modeloListaProgramadores.addElement(programador);
                break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // Coloca la ventana en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
