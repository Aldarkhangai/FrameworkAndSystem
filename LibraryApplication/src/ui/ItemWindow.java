package ui;

import java.util.ArrayList;
import java.util.List;

import business.Auth;
import business.Item;
import business.ItemCopy;
import controller.ControllerInterface;
import controller.SystemController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import login.cor.LoginException;

public class ItemWindow extends Stage {
	public static final ItemWindow INSTANCE = new ItemWindow();

	public static final ControllerInterface controllerInterface = new SystemController();

	private TableView<Item> tableItem;
	private TableView<ItemCopy> tableItemCopy;
	

	private List<Item> items = new ArrayList<>();

	private GridPane itemGrid = new GridPane();

	private Item selectedItem;

	private Label label0, label1, label2;//, label3, label4 = new Label();

	private TextField textField0, textField1, textField2 ;//,textField3, textField4 = new TextField("");

	private Button clearBtn = new Button("Clear");
	private Button backBtn = new Button("Back");
	private Button saveBtn = new Button("Save");

	private HBox btnBox = new HBox(10);

	private boolean isInitialized = false;

	private Alert alert = new Alert(AlertType.WARNING, "");

	private ItemWindow() {
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public void clear() {
		textField0 = new TextField("");
		textField1 = new TextField("");
		textField2 = new TextField("");
		//textField3 = new TextField("");
		//textField4 = new TextField("");
	}

	@SuppressWarnings("unchecked")
	private void setItemTableColumns() {
		tableItem = new TableView<Item>();
		TableColumn<Item, String> itemNameCol = new TableColumn<>("Item Name");
		itemNameCol.setMinWidth(200);
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		itemNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tableItem.getColumns().addAll(itemNameCol);
		TableColumn<Item, String> itemIDCol = new TableColumn<>("Item ID");
		itemIDCol.setMinWidth(100);
		itemIDCol.setCellValueFactory(new PropertyValueFactory<Item, String>("id"));
		itemIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tableItem.getColumns().addAll(itemIDCol);
		TableColumn<Item, Integer> itemDescription = new TableColumn<>("Item Description");
		itemDescription.setMinWidth(100);
		itemDescription.setCellValueFactory(new PropertyValueFactory<Item, Integer>("description"));
		tableItem.getColumns().addAll(itemDescription);
	}

	@SuppressWarnings({ "unchecked" })
	private void setItemCopyTableColumns() {
		tableItemCopy = new TableView<ItemCopy>();
		TableColumn<ItemCopy, Integer> itemIDCol = new TableColumn<>("Num Copy");
		itemIDCol.setMinWidth(200);
		itemIDCol.setCellValueFactory(new PropertyValueFactory<ItemCopy, Integer>("copyNum"));
		tableItemCopy.getColumns().addAll(itemIDCol);
	}

	private void showItemCopiesGrid() {
		clear();
		setTitle("Item copy");
		setItemCopyTableColumns();
		tableItemCopy.setItems(FXCollections.observableArrayList(selectedItem.getItemCopies()));
		backBtn.setOnAction(evt -> {
			createMainGrid();
			setScene(itemGrid, 500, 400);
		});
		setGrid();
		itemGrid.add(tableItemCopy, 0, 0);
		itemGrid.add(backBtn, 0, 1);
	}


	private void addItemGrid() {
		clear();
		setTitle("Add Item");

		label0 = new Label("ID : ");
		label1 = new Label("NAME : ");
		label2 = new Label("description : ");
		//label3 = new Label("Max Checkout length : ");

		saveBtn.setOnAction(evt -> {
			String id = textField0.getText();
			String name = textField1.getText();
			String description =textField1.getText();
			if (id.isEmpty() || name.isEmpty() || description.isEmpty()) {
				alert = new Alert(AlertType.WARNING, "All field should be nonempty!", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			if (!id.matches("\\d\\d-\\d\\d\\d\\d\\d")) {
				alert = new Alert(AlertType.WARNING, "id field template (12-34567)!", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			for (char x : id.toCharArray()) {
				if (x != '-' && (x < '0' || x > '9')) {
					alert = new Alert(AlertType.WARNING, "ID field template (12-34567)!", ButtonType.YES);
					alert.showAndWait();
					return;
				}
			}
			if (!items.isEmpty()) {
				for (Item item : items) {
					if (item.getName().equalsIgnoreCase(name) || item.getId().equalsIgnoreCase(id)) {
						alert = new Alert(AlertType.WARNING,
								"Your title and id duplicated, you should change your item info!", ButtonType.YES);
						alert.showAndWait();
						return;
					}
				}
			}
//			try {
//				maxLenght = Integer.parseInt(textField2.getText());
//			} catch (NumberFormatException ex) {
//				alert = new Alert(AlertType.WARNING, "Max length field should be numeric!", ButtonType.YES);
//				alert.showAndWait();
//				return;
//			}
			Item newItem = new Item(id, name,description);
			items.add(newItem);
			controllerInterface.loadItemMap(items);
			createMainGrid();
			setScene(itemGrid, 500, 400);
		});
		clearBtn.setOnAction(evt -> {
			addItemGrid();
			setScene(itemGrid, 500, 600);
		});
		backBtn.setOnAction(evt -> {
			createMainGrid();
			setScene(itemGrid, 500, 400);
		});

		setGrid();
		itemGrid.add(label0, 0, 0, 4, 1);
		itemGrid.add(textField0, 0, 1, 4, 1);
		itemGrid.add(label1, 0, 2, 4, 1);
		itemGrid.add(textField1, 0, 3, 4, 1);
		//itemGrid.add(label3, 0, 4, 4, 1);
	//	itemGrid.add(textField3, 0, 5, 4, 1);
		itemGrid.add(label2, 0, 6, 4, 1);
		itemGrid.add(textField2, 0, 7, 4, 1);

		btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER_RIGHT);

		itemGrid.add(btnBox, 0, 10, 4, 1);

		btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(saveBtn);
		btnBox.getChildren().add(clearBtn);
		btnBox.getChildren().add(backBtn);
		itemGrid.add(btnBox, 0, 12, 4, 1);
	}

	private void createMainGrid() {
		setTitle("Items list");
		setItemTableColumns();
		tableItem.setItems(FXCollections.observableArrayList(items));
		Label searchLabel = new Label("Search Item :");
		TextField searchText = new TextField("");

		Button addItemCopyButton = new Button("Add Item copy");
		Button addButton = new Button("Add Item");
		Button seeItemCopyButton = new Button("See Item copy");

		addItemCopyButton.setOnAction(evt -> {
			selectedItem = tableItem.getSelectionModel().getSelectedItem();
			if (selectedItem == null) {
				alert = new Alert(AlertType.WARNING, "Please select an Item !", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			selectedItem.addCopy();
			for(Item it:items) {
				if(it.getName().equals(selectedItem.getName())) {
					items.remove(it);
					items.add(selectedItem);
				}
					
			}
			controllerInterface.loadItemMap(items);
			tableItem.refresh();
		});
		addButton.setOnAction(evt -> {
			addItemGrid();
			setScene(itemGrid, 500, 600);
		});
		seeItemCopyButton.setOnAction(evt -> {
			selectedItem = tableItem.getSelectionModel().getSelectedItem();
			if (selectedItem == null) {
				alert = new Alert(AlertType.WARNING, "Please select a Item!", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			if (selectedItem.getNumCopies() == 0) {
				alert = new Alert(AlertType.WARNING, "Item copy not found!", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			showItemCopiesGrid();
			setScene(itemGrid, 500, 400);
		});
		searchText.setOnAction(evt -> {
			if (searchText.getText().isEmpty()) {
				tableItem.setItems(FXCollections.observableArrayList(items));
				alert = new Alert(AlertType.WARNING, "Insert search value!", ButtonType.YES);
				alert.showAndWait();
				return;
			}
			String searchValue = searchText.getText();
			List<Item> filteredItems = new ArrayList<>();
			if (!items.isEmpty()) {
				for (Item item : items) {
					if (!item.getId().toLowerCase().contains(searchValue.toLowerCase())
							&& !item.getName().toLowerCase().contains(searchValue.toLowerCase()))
						continue;

					filteredItems.add(item);
				}
				if (!filteredItems.isEmpty()) {
					tableItem.setItems(FXCollections.observableArrayList(filteredItems));
				} else {
					tableItem.setItems(FXCollections.observableArrayList(items));
					alert = new Alert(AlertType.WARNING, "Item not found!", ButtonType.YES);
					alert.showAndWait();
				}
			}
			searchText.setText("");
		});
		backBtn.setOnAction(evt -> {
			ControllerInterface c = new SystemController();
			HomePage.hideAllWindows();
			//HomePage.primStage().show();
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
		});

		setGrid();
		btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER_RIGHT);
		btnBox.getChildren().add(searchLabel);
		btnBox.getChildren().add(searchText);
		itemGrid.add(btnBox, 0, 0, 1, 1);
		itemGrid.add(tableItem, 0, 1, 2, 1);
		btnBox = new HBox(10);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().add(addItemCopyButton);
		btnBox.getChildren().add(addButton);
		btnBox.getChildren().add(seeItemCopyButton);
		btnBox.getChildren().add(backBtn);
		itemGrid.add(btnBox, 0, 2);
	}

	private void setGrid() {
		itemGrid = new GridPane();
		itemGrid.setAlignment(Pos.CENTER);
		itemGrid.setVgap(10);
		itemGrid.setHgap(10);
		itemGrid.setPadding(new Insets(10, 15, 10, 15));
	}

	public void init() {
		items = controllerInterface.items();
		createMainGrid();
		setScene(itemGrid, 500, 400);
	}

	private void setScene(GridPane grid, int SCENE_WIDTH, int SCENE_HEIGHT) {
		Scene scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		setScene(scene);
	}
}
