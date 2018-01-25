package ui;

import java.util.List;

import business.Auth;
import business.Member;
import business.User;
import controller.ControllerInterface;
import controller.SystemController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.cor.LoginException;

public class AdminUserWindow extends Stage implements LibWindow {
	public static final AdminUserWindow INSTANCE = new AdminUserWindow();
	SystemController cont = new SystemController();
	int index = -1;
	BorderPane border = new BorderPane();
	GridPane gridAdd = new GridPane();
	GridPane gridMain = new GridPane();
	Scene scene;
	Button btnLoadAdd = new Button();
	Button btnLoadCheckout = new Button();
	Button btnBack = new Button();
	boolean isGridFill = false;
	TableView<User> tUser = new TableView<User>();
	private Text messageBar = new Text();
	private boolean isInitialized = false;
	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	public void clear() {
		messageBar.setText("");
	}

	private AdminUserWindow() {
	}

	@Override
	public void init() {
		gridMain.setId("top-container");
		gridMain.setAlignment(Pos.BASELINE_LEFT);
		gridMain.setHgap(10);
		gridMain.setVgap(10);
		Button backBtn = new Button("Back to Admin");
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ControllerInterface c = new SystemController();
				HomePage.hideAllWindows();
				try {
					if (c.login(HomePage.loginTextField.getText().trim(), HomePage.pwTextField.getText().trim())
							.equals(Auth.ADMIN)) {
						if (!AdminWindow.INSTANCE.isInitialized()) {
							AdminWindow.INSTANCE.init();
						}
						AdminWindow.INSTANCE.clear();
						AdminWindow.INSTANCE.show();
					}
					if (c.login(HomePage.loginTextField.getText().trim(), HomePage.pwTextField.getText().trim())
							.equals(Auth.BOTH)) {
						if (!LibAdminWindow.INSTANCE.isInitialized()) {
							LibAdminWindow.INSTANCE.init();
						}
						LibAdminWindow.INSTANCE.clear();
						LibAdminWindow.INSTANCE.show();
					}
				} catch (LoginException ex) {
					ex.printStackTrace();
				}
			}
		});

		if (isGridFill == false) {
			scene = new Scene(border, 600, 400);
			scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			setScene(scene);
			fillAddFields();
		}
		
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	private void fillAddFields() {
		gridAdd.setId("top-container");
		gridAdd.setAlignment(Pos.BASELINE_LEFT);
		gridAdd.setHgap(10);
		gridAdd.setVgap(10);
		gridAdd.setPadding(new Insets(25, 0, 25, 25));
		Text scenetitle = new Text("Admin user Window");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); // Tahoma
		Label lUserId = new Label("User ID:");
		TextField txtUserId = new TextField();
		Label lPassword = new Label("User pass:");
		PasswordField txtPassword = new PasswordField();
		Label lLastName = new Label("Auth:");
		ComboBox cmbAuth = new ComboBox();
		tUser.setEditable(false);
		if (isGridFill == false) {
			TableColumn<User, String> userIdCol = new TableColumn<User, String>("User ID");
			TableColumn<User, String> userAuthCol = new TableColumn<User, String>("User Auth");
			userIdCol.setMinWidth(120);
			userAuthCol.setMinWidth(120);

			userIdCol.setCellValueFactory(new PropertyValueFactory<User, String>("Id"));
			userIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
			userAuthCol.setCellValueFactory(new PropertyValueFactory<User, String>("authorization"));
			userAuthCol.setCellFactory(ComboBoxTableCell.forTableColumn());
			tUser.getColumns().addAll(userIdCol, userAuthCol);
			cmbAuth.getItems().addAll(cont.allAuth());
			isGridFill = true;
		}

		Button btnBack = new Button("Back to Admin");
		btnBack.setPrefWidth(150);
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ControllerInterface c = new SystemController();
				txtUserId.clear();
				txtPassword.clear();
				HomePage.hideAllWindows();
				try {
					if (c.login(HomePage.loginTextField.getText().trim(), HomePage.pwTextField.getText().trim())
							.equals(Auth.ADMIN)) {
						if (!AdminWindow.INSTANCE.isInitialized()) {
							AdminWindow.INSTANCE.init();
						}
						AdminWindow.INSTANCE.clear();
						AdminWindow.INSTANCE.show();
					}
					if (c.login(HomePage.loginTextField.getText().trim(), HomePage.pwTextField.getText().trim())
							.equals(Auth.BOTH)) {
						if (!LibAdminWindow.INSTANCE.isInitialized()) {
							LibAdminWindow.INSTANCE.init();
						}
						LibAdminWindow.INSTANCE.clear();
						LibAdminWindow.INSTANCE.show();
					}
				} catch (LoginException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		Button btnClear = new Button("Clear fields");
		btnClear.setPrefWidth(150);
		btnClear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				txtUserId.clear();
				txtPassword.clear();
			}
		});
		Button btnAddUser = new Button("Add user");
		btnAddUser.setPrefWidth(150);
		btnAddUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<User> data = cont.allUsers();
				boolean isCreatedMember = false;
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getId().equals(txtUserId.getText().trim())) {
						Alert alert = new Alert(AlertType.WARNING, "Duplicated member ID!!!", ButtonType.OK);
						alert.showAndWait();
						isCreatedMember = true;
					}
				}
				if (isCreatedMember == false) {
					String id = txtUserId.getText();
					String password = txtPassword.getText();
					Auth auth = (Auth)cmbAuth.getValue();
					if (!id.trim().isEmpty() && !password.trim().isEmpty());
					cont.addUser(id, password, auth);
						fillTableView();
						txtUserId.clear();
						txtPassword.clear();
						
					} else {
						Alert alert = new Alert(AlertType.WARNING, "Please fill out blank fields!!!",
								ButtonType.OK);
						alert.showAndWait();
					}
			}
		});
		Button btnRemoveUser = new Button("Remove user");
		btnRemoveUser.setPrefWidth(150);
		btnRemoveUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to remove this member?", ButtonType.YES,
						ButtonType.NO, ButtonType.CANCEL);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.YES) {
					String id = txtUserId.getText();
					String password = txtPassword.getText();
					Auth auth = (Auth)cmbAuth.getValue();
					cont.deleteUser(id, password, auth);
					fillTableView();
					txtUserId.clear();
					txtPassword.clear();
				}
			}
		});
		tUser.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				SystemController cont = new SystemController();
				if (tUser.getSelectionModel().getSelectedIndex() > -1) {
					index = tUser.getSelectionModel().getSelectedIndex();
					ObservableList<User> data = cont.allUsers();
					data.get(index);
					txtUserId.setText(data.get(index).getId());
					txtPassword.setText(data.get(index).getPassword());
					cmbAuth.setPromptText(data.get(index).getAuthorization().toString());
				}
			}
		});
		Button btnEditUser = new Button("Edit user");
		btnEditUser.setPrefWidth(150);
		btnEditUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ObservableList<User> data = cont.allUsers();
				String id = txtUserId.getText();
				String password = txtPassword.getText();
				Auth auth = (Auth)cmbAuth.getValue();
				cont.updateUser(id, password, auth);
				fillTableView();
				txtUserId.clear();
				txtPassword.clear();
			}
		});
		fillTableView();
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		tUser.setMinWidth(300);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.getChildren().add(tUser);

		gridAdd.add(lUserId, 0, 1);
		gridAdd.add(txtUserId, 1, 1);
		gridAdd.add(lPassword, 0, 2);
		gridAdd.add(txtPassword, 1, 2);
		gridAdd.add(lLastName, 0, 3);
		gridAdd.add(cmbAuth, 1, 3);
		gridAdd.add(btnAddUser, 1, 6);
		gridAdd.add(btnEditUser, 1, 7);
		gridAdd.add(btnRemoveUser, 1, 8);
		gridAdd.add(btnClear, 1, 9);
		gridAdd.add(btnBack, 1, 10);
		border.setLeft(gridAdd);
		border.setRight(hbox);

		}

	private void fillTableView() {
		ObservableList<User> data = cont.allUsers();
		tUser.setItems(data);
	}
}