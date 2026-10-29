package com.munoz.felipe.controller;

import com.munoz.felipe.dao.UserDao;
import com.munoz.felipe.dto.UserDto;
import com.munoz.felipe.service.UserService;
import com.munoz.felipe.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class UserController {

    // Table
    @FXML
    public TableView tableUsuarios;

    @FXML
    public TableColumn idColum;

    @FXML
    public TableColumn nombreColum;

    @FXML
    public TableColumn rutColum;

    @FXML
    public TableColumn edadColum;

    // Data
    @FXML
    public TextField nombreField;

    @FXML
    public TextField rutField;

    @FXML
    public Spinner<Integer> edadField;

    private ObservableList<UserDto> usuarios;

    @FXML
    public Label msgLabel;

    @FXML
    public Button eliminarBtn;

    @FXML
    public Button saveBtn;

    UserDao usersDao = new UserDao();
    private UserDto selectedUser;

    @FXML
    public void initialize() {
        idColum.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        rutColum.setCellValueFactory(new PropertyValueFactory<>("rut"));
        edadColum.setCellValueFactory(new PropertyValueFactory<>("edad"));

        this.usuarios = FXCollections.observableArrayList();
        this.tableUsuarios.setItems(this.usuarios);
    }

    public void addMouseClicked(MouseEvent mouseEvent) throws IOException {
        UserDto usuario = new UserDto();

        usuario.setNombre(nombreField.getText());
        usuario.setRut(rutField.getText());
        usuario.setEdad(edadField.getValue());

        if (nombreField.getText().isEmpty() || rutField.getText().isEmpty()){
            msgLabel.setTextFill(Color.RED);
            msgLabel.setText("Rellene todo los valores");
        } else {
            usuario.setId(setID());
            usuarios.add(usuario);
            resetValues();

            msgLabel.setTextFill(Color.GREEN);
            msgLabel.setText("Se ha agregado un usuario nuevo!");
        }
    }
    public void onLeer(MouseEvent mouseEvent) throws  IOException {
        String data = this.usersDao.leer();
        System.out.println("Data: " + data);
    }
    public void onGuardar(MouseEvent mouseEvent) throws IOException {
        usersDao.guardar(UserService.getUsuarios());
    }

    @FXML
    public void seleccionarUsuario(MouseEvent mouseEvent){
        UserDto user = (UserDto) this.tableUsuarios.getSelectionModel().getSelectedItem();
        selectedUser = user;
        eliminarBtn.setDisable(false);
        System.out.println("Click!");
    }

    @FXML
    public void eliminarUsuario(MouseEvent mouseEvent){
        UserService.eliminarUsuario(selectedUser);
        usuarios.setAll(UserService.getUsuarios());
        eliminarBtn.setDisable(true);
    }

    private void resetValues(){
        nombreField.setText("");
        rutField.setText("");
        edadField.getValueFactory().setValue(18);
    }

    private Integer idUsuario = 0;
    private Integer setID() {
        idUsuario = idUsuario + 1;
        return idUsuario;
    }
}