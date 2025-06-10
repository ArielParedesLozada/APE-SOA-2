package com.mycompany.frontend.presentation.controller;

import com.mycompany.frontend.domain.entity.Color;
import com.mycompany.frontend.domain.entity.Marca;
import com.mycompany.frontend.domain.entity.Modelo;
import com.mycompany.frontend.domain.entity.Vehiculo;
import com.mycompany.frontend.domain.usecase.CreateVehiculoUseCase;
import com.mycompany.frontend.domain.usecase.DeleteVehiculoUseCase;
import com.mycompany.frontend.domain.usecase.GetAllColorsUseCase;
import com.mycompany.frontend.domain.usecase.GetAllMarcasUseCase;
import com.mycompany.frontend.domain.usecase.GetAllModelosByMarcaUseCase;
import com.mycompany.frontend.domain.usecase.GetAllModelosUseCase;
import com.mycompany.frontend.domain.usecase.GetAllVehiculosUseCase;
import com.mycompany.frontend.domain.usecase.UpdateVehiculoUseCase;
import com.mycompany.frontend.presentation.view.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MainController {

    private final MainFrame view;
    private final GetAllMarcasUseCase getAllMarcas;
    private final GetAllModelosUseCase getAllModelos;
    private final GetAllColorsUseCase getAllColors;
    private final GetAllVehiculosUseCase getAllVehiculos;
    private final CreateVehiculoUseCase createVehiculo;
    private final UpdateVehiculoUseCase updateVehiculo;
    private final DeleteVehiculoUseCase deleteVehiculo;

    // al inicio de la clase:
    private int lastClickedRow = -1;
    private Integer selectedId = null;

    // caches para mapear nombres a IDs
    private List<Marca> marcasCache = Collections.emptyList();
    private List<Modelo> modelosCache = Collections.emptyList();
    private List<Color> colorsCache = Collections.emptyList();

    public MainController(
            MainFrame view,
            GetAllMarcasUseCase getAllMarcas,
            GetAllModelosUseCase getModelos,
            GetAllColorsUseCase getAllColors,
            GetAllVehiculosUseCase getAllVehiculos,
            CreateVehiculoUseCase createVehiculo,
            UpdateVehiculoUseCase updateVehiculo,
            DeleteVehiculoUseCase deleteVehiculo
    ) {
        this.view = view;
        this.getAllMarcas = getAllMarcas;
        this.getAllModelos = getModelos;
        this.getAllColors = getAllColors;
        this.getAllVehiculos = getAllVehiculos;
        this.createVehiculo = createVehiculo;
        this.updateVehiculo = updateVehiculo;
        this.deleteVehiculo = deleteVehiculo;
    }

    public void initController() {
        loadCatalogs();
        loadVehiculos();

        view.getBtnLoadCatalogs().addActionListener(e -> loadCatalogs());
//         view.getComboMarca().addActionListener(e -> onMarcaChanged());
        view.getBtnCreateVehiculo().addActionListener(e -> onSaveVehiculo());
        view.getBtnDeleteVehiculo().addActionListener(e -> onDeleteVehiculo());
        view.getBtnClearForm().addActionListener(e -> clearForm());
        view.getTableVehiculos()
                .getSelectionModel()
                .addListSelectionListener(e -> onTableSelection());

        view.getTableVehiculos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = view.getTableVehiculos();
                int clickedRow = table.rowAtPoint(e.getPoint());
                // Si clicaste en el header o fuera de filas, ignoramos
                if (clickedRow < 0) {
                    return;
                }

                if (clickedRow == lastClickedRow) {
                    // Segundo clic sobre la misma fila → deseleccionar
                    table.clearSelection();
                    clearForm();
                    lastClickedRow = -1;
                } else {
                    // Primer clic sobre una fila distinta → dejamos que el ListSelectionListener la marque
                    lastClickedRow = clickedRow;
                }
            }
        });

    }

    private void loadCatalogs() {
        loadMarcas();
        loadColors();
        loadModelos();
    }

    private void loadMarcas() {
        SwingUtilities.invokeLater(() -> {
            try {
                marcasCache = getAllMarcas.execute();
                JComboBox<String> cb = view.getComboMarca();
                cb.removeAllItems();
                // 1) placeholder
                cb.addItem("Seleccione");
                // 2) luego las marcas reales
                for (Marca m : marcasCache) {
                    cb.addItem(m.getNombre());
                }
                // 3) dejar seleccionado el placeholder
                cb.setSelectedIndex(0);

                // al cambiar de marca, recargas modelos…
                // view.getComboMarca().addActionListener(e -> onMarcaChanged());
            } catch (IOException ex) {
                showError("Error al cargar marcas:\n" + ex.getMessage());
            }
        });
    }

    /*
    private void onMarcaChanged() {
        String nombreMarca = (String) view.getComboMarca().getSelectedItem();
        if (nombreMarca == null) {
            return;
        }
        int marcaId = marcasCache.stream()
                .filter(m -> m.getNombre().equals(nombreMarca))
                .map(Marca::getId)
                .findFirst()
                .orElse(0);
        loadModelos(marcaId);
    }
     */
    private void loadModelos() {
        SwingUtilities.invokeLater(() -> {
            try {
                modelosCache = getAllModelos.execute();
                JComboBox<String> cb = view.getComboModelo();
                cb.removeAllItems();
                cb.addItem("Seleccione");
                for (Modelo m : modelosCache) {
                    cb.addItem(m.getNombre());
                }
                cb.setSelectedIndex(0);
            } catch (IOException ex) {
                showError("Error al cargar modelos:\n" + ex.getMessage());
            }
        });
    }

    private void loadColors() {
        SwingUtilities.invokeLater(() -> {
            try {
                colorsCache = getAllColors.execute();
                JComboBox<String> cb = view.getComboColor();
                cb.removeAllItems();
                cb.addItem("Seleccione");
                for (Color c : colorsCache) {
                    cb.addItem(c.getNombre());
                }
                cb.setSelectedIndex(0);
            } catch (IOException ex) {
                showError("Error al cargar colores:\n" + ex.getMessage());
            }
        });
    }

    private void loadVehiculos() {
        SwingUtilities.invokeLater(() -> {
            try {
                List<Vehiculo> items = getAllVehiculos.execute();
                DefaultTableModel model = (DefaultTableModel) view
                        .getTableVehiculos()
                        .getModel();
                model.setRowCount(0);
                for (Vehiculo v : items) {
                    model.addRow(new Object[]{
                        v.getId(),
                        v.getModelo().getNombre(), // primero Modelo
                        v.getMarca().getNombre(), // luego Marca
                        v.getPlaca(),
                        v.getChasis(),
                        v.getAnio(),
                        v.getColor().getNombre()
                    });
                }
            } catch (IOException ex) {
                showError("Error al cargar vehículos:\n" + ex.getMessage());
            }
        });
    }

    private void onSaveVehiculo() {
        try {
            String placa = view.getTxtPlaca().getText().trim();
            String chasis = view.getTxtChasis().getText().trim();
            int anio = Integer.parseInt(view.getTxtAnio().getText().trim());

            String nomMarca = (String) view.getComboMarca().getSelectedItem();
            String nomModelo = (String) view.getComboModelo().getSelectedItem();
            String nomColor = (String) view.getComboColor().getSelectedItem();

            Marca marca = marcasCache.stream()
                    .filter(m -> m.getNombre().equals(nomMarca))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Marca no encontrada"));
            Modelo modelo = modelosCache.stream()
                    .filter(m -> m.getNombre().equals(nomModelo))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Modelo no encontrado"));
            Color color = colorsCache.stream()
                    .filter(c -> c.getNombre().equals(nomColor))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Color no encontrado"));

            // Usa selectedId para decidir CREATE vs UPDATE
            Vehiculo v = new Vehiculo(
                    selectedId != null ? selectedId : 0,
                    placa, chasis, anio,
                    marca, modelo, color
            );

            if (selectedId == null) {
                createVehiculo.execute(v);
            } else {
                updateVehiculo.execute(v);
            }

            loadVehiculos();
            clearForm();
        } catch (NumberFormatException nfe) {
            showError("Año inválido");
        } catch (IOException ex) {
            showError("Error al guardar vehículo:\n" + ex.getMessage());
        }
    }

    private void onDeleteVehiculo() {
        int row = view.getTableVehiculos().getSelectedRow();
        if (row < 0) {
            showError("Selecciona una fila para eliminar");
            return;
        }
        int id = (int) view.getTableVehiculos().getValueAt(row, 0);
        try {
            deleteVehiculo.execute(id);
            loadVehiculos();
            clearForm();
        } catch (IOException ex) {
            showError("Error al eliminar vehículo:\n" + ex.getMessage());
        }
    }

    private void onTableSelection() {
        int row = view.getTableVehiculos().getSelectedRow();
        if (row < 0) {
            selectedId = null;
            return;
        }

        // 1) Guarda el ID de la fila seleccionada (columna 0)
        selectedId = (Integer) view.getTableVehiculos().getValueAt(row, 0);

        // 2) Rellena combos y campos
        String modeloNombre = view.getTableVehiculos().getValueAt(row, 1).toString();
        String marcaNombre = view.getTableVehiculos().getValueAt(row, 2).toString();
        view.getComboModelo().setSelectedItem(modeloNombre);
        view.getComboMarca().setSelectedItem(marcaNombre);
        view.getTxtPlaca().setText(view.getTableVehiculos().getValueAt(row, 3).toString());
        view.getTxtChasis().setText(view.getTableVehiculos().getValueAt(row, 4).toString());
        view.getTxtAnio().setText(view.getTableVehiculos().getValueAt(row, 5).toString());
        view.getComboColor().setSelectedItem(view.getTableVehiculos().getValueAt(row, 6).toString());
    }

    private void clearForm() {
        selectedId = null;
        view.getTxtPlaca().setText("");
        view.getTxtChasis().setText("");
        view.getTxtAnio().setText("");
        view.getComboMarca().setSelectedIndex(0);
        view.getComboModelo().setSelectedIndex(0);
        view.getComboColor().setSelectedIndex(0);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                view, message, "Error", JOptionPane.ERROR_MESSAGE
        );
    }
}
