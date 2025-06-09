package com.mycompany.frontend;

import com.mycompany.frontend.data.network.ApiService;
import com.mycompany.frontend.data.repository.ColorRepositoryImpl;
import com.mycompany.frontend.data.repository.MarcaRepositoryImpl;
import com.mycompany.frontend.data.repository.ModeloRepositoryImpl;
import com.mycompany.frontend.data.repository.VehiculoRepositoryImpl;
import com.mycompany.frontend.domain.usecase.CreateVehiculoUseCase;
import com.mycompany.frontend.domain.usecase.DeleteVehiculoUseCase;
import com.mycompany.frontend.domain.usecase.GetAllColorsUseCase;
import com.mycompany.frontend.domain.usecase.GetAllMarcasUseCase;
import com.mycompany.frontend.domain.usecase.GetAllModelosByMarcaUseCase;
import com.mycompany.frontend.domain.usecase.GetAllVehiculosUseCase;
import com.mycompany.frontend.domain.usecase.UpdateVehiculoUseCase;
import com.mycompany.frontend.presentation.controller.MainController;
import com.mycompany.frontend.presentation.view.MainFrame;

public class Frontend {
    public static void main(String[] args) {
        // 1) Configura tu cliente HTTP apuntando al backend
        ApiService api = new ApiService("http://localhost:3000");

        // 2) Crea las implementaciones de repositorio
        var colorRepo   = new ColorRepositoryImpl(api);
        var marcaRepo   = new MarcaRepositoryImpl(api);
        var modeloRepo  = new ModeloRepositoryImpl(api);
        var vehRepo     = new VehiculoRepositoryImpl(api);

        // 3) Instancia tus casos de uso
        var getAllMarcas       = new GetAllMarcasUseCase(marcaRepo);
        var getAllModelos      = new GetAllModelosByMarcaUseCase(modeloRepo);
        var getAllColors       = new GetAllColorsUseCase(colorRepo);
        var getAllVehiculos    = new GetAllVehiculosUseCase(vehRepo);
        var createVehiculo     = new CreateVehiculoUseCase(vehRepo);
        var updateVehiculo     = new UpdateVehiculoUseCase(vehRepo);
        var deleteVehiculo     = new DeleteVehiculoUseCase(vehRepo);

        // 4) Crea la vista y el controlador
        MainFrame frame = new MainFrame();
        MainController controller = new MainController(
            frame,
            getAllMarcas,
            getAllModelos,
            getAllColors,
            getAllVehiculos,
            createVehiculo,
            updateVehiculo,
            deleteVehiculo
        );

        // 5) Inicializa listeners y carga inicial
        controller.initController();

        // 6) Muestra la ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
