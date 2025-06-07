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
import com.mycompany.frontend.domain.usecase.GetAllVehiculosUseCase;
import com.mycompany.frontend.domain.usecase.UpdateVehiculoUseCase;
import com.mycompany.frontend.presentation.view.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MainController {
    private final MainFrame view;
    private final GetAllMarcasUseCase    getAllMarcas;
    private final GetAllModelosByMarcaUseCase getModelosByMarca;
    private final GetAllColorsUseCase    getAllColors;
    private final GetAllVehiculosUseCase getAllVehiculos;
    private final CreateVehiculoUseCase  createVehiculo;
    private final UpdateVehiculoUseCase  updateVehiculo;
    private final DeleteVehiculoUseCase  deleteVehiculo;

    // caches para mapear nombres a IDs
    private List<Marca>  marcasCache  = Collections.emptyList();
    private List<Modelo> modelosCache = Collections.emptyList();
    private List<Color>  colorsCache  = Collections.emptyList();

    public MainController(
        MainFrame view,
        GetAllMarcasUseCase getAllMarcas,
        GetAllModelosByMarcaUseCase getModelosByMarca,
        GetAllColorsUseCase getAllColors,
        GetAllVehiculosUseCase getAllVehiculos,
        CreateVehiculoUseCase createVehiculo,
        UpdateVehiculoUseCase updateVehiculo,
        DeleteVehiculoUseCase deleteVehiculo
    ) {
        this.view               = view;
        this.getAllMarcas       = getAllMarcas;
        this.getModelosByMarca  = getModelosByMarca;
        this.getAllColors       = getAllColors;
        this.getAllVehiculos    = getAllVehiculos;
        this.createVehiculo     = createVehiculo;
        this.updateVehiculo     = updateVehiculo;
        this.deleteVehiculo     = deleteVehiculo;
    }

    public void initController() {
        loadCatalogs();
        loadVehiculos();

        view.getBtnLoadCatalogs().addActionListener(e -> loadCatalogs());
        view.getComboMarca().addActionListener(e -> onMarcaChanged());
        view.getBtnCreateVehiculo().addActionListener(e -> onSaveVehiculo());
        view.getBtnDeleteVehiculo().addActionListener(e -> onDeleteVehiculo());
        view.getBtnClearForm().addActionListener(e -> clearForm());
        view.getTableVehiculos()
            .getSelectionModel()
            .addListSelectionListener(e -> onTableSelection());
    }

    private void loadCatalogs() {
        loadMarcas();
        loadColors();
    }

    private void loadMarcas() {
        SwingUtilities.invokeLater(() -> {
            try {
                marcasCache = getAllMarcas.execute();
                JComboBox<String> cb = view.getComboMarca();
                cb.removeAllItems();
                for (Marca m : marcasCache) {
                    cb.addItem(m.getNombre());
                }
                view.getComboModelo().removeAllItems();
            } catch (IOException ex) {
                showError("Error al cargar marcas:\n" + ex.getMessage());
            }
        });
    }

    private void onMarcaChanged() {
        String nombreMarca = (String) view.getComboMarca().getSelectedItem();
        if (nombreMarca == null) return;
        int marcaId = marcasCache.stream()
            .filter(m -> m.getNombre().equals(nombreMarca))
            .map(Marca::getId)
            .findFirst()
            .orElse(0);
        loadModelos(marcaId);
    }

    private void loadModelos(int marcaId) {
        SwingUtilities.invokeLater(() -> {
            try {
                modelosCache = getModelosByMarca.execute(marcaId);
                JComboBox<String> cb = view.getComboModelo();
                cb.removeAllItems();
                for (Modelo m : modelosCache) {
                    cb.addItem(m.getNombre());
                }
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
                for (Color c : colorsCache) {
                    cb.addItem(c.getNombre());
                }
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
                        v.getMarca().getNombre(),
                        v.getModelo().getNombre(),
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
            int id = view.getTxtId().isEnabled()
                    ? Integer.parseInt(view.getTxtId().getText())
                    : 0;
            String placa  = view.getTxtPlaca().getText().trim();
            String chasis = view.getTxtChasis().getText().trim();
            int anio      = Integer.parseInt(view.getTxtAnio().getText().trim());

            String nomMarca  = (String) view.getComboMarca().getSelectedItem();
            String nomModelo = (String) view.getComboModelo().getSelectedItem();
            String nomColor  = (String) view.getComboColor().getSelectedItem();

            Marca marca   = new Marca(
                marcasCache.stream()
                    .filter(m -> m.getNombre().equals(nomMarca))
                    .map(Marca::getId)
                    .findFirst().orElse(0),
                nomMarca
            );
            Modelo modelo = new Modelo(
                modelosCache.stream()
                    .filter(m -> m.getNombre().equals(nomModelo))
                    .map(Modelo::getId)
                    .findFirst().orElse(0),
                nomModelo
            );
            Color color   = new Color(
                colorsCache.stream()
                    .filter(c -> c.getNombre().equals(nomColor))
                    .map(Color::getId)
                    .findFirst().orElse(0),
                nomColor
            );

            Vehiculo v = new Vehiculo(id, placa, chasis, anio, marca, modelo, color);

            if (id == 0) {
                createVehiculo.execute(v);
            } else {
                updateVehiculo.execute(v);
            }
            loadVehiculos();
            clearForm();
        } catch (NumberFormatException nfe) {
            showError("ID o Año inválido");
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
        if (row < 0) return;
        view.getTxtId().setText(
            view.getTableVehiculos().getValueAt(row, 0).toString()
        );
        view.getTxtId().setEnabled(true);

        view.getComboMarca().setSelectedItem(
            view.getTableVehiculos().getValueAt(row, 1).toString()
        );
        view.getComboModelo().setSelectedItem(
            view.getTableVehiculos().getValueAt(row, 2).toString()
        );
        view.getTxtPlaca().setText(
            view.getTableVehiculos().getValueAt(row, 3).toString()
        );
        view.getTxtChasis().setText(
            view.getTableVehiculos().getValueAt(row, 4).toString()
        );
        view.getTxtAnio().setText(
            view.getTableVehiculos().getValueAt(row, 5).toString()
        );
        view.getComboColor().setSelectedItem(
            view.getTableVehiculos().getValueAt(row, 6).toString()
        );
    }

    private void clearForm() {
        view.getTxtId().setText("");
        view.getTxtId().setEnabled(false);
        view.getTxtPlaca().setText("");
        view.getTxtChasis().setText("");
        view.getTxtAnio().setText("");
        view.getComboMarca().setSelectedIndex(-1);
        view.getComboModelo().removeAllItems();
        view.getComboColor().setSelectedIndex(-1);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            view, message, "Error", JOptionPane.ERROR_MESSAGE
        );
    }
}
