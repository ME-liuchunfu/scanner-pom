package xin.spring.javafx.views.tree;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description
 * @date 2019/12/27
 */

public class TreeViewDemo extends Application {

    public Parent createContent() {

        /* layout */
        BorderPane layout = new BorderPane();

        /* layout -> center */

        /* initialize TreeView<CustomItem> object */
        TreeView<CustomItem> tree = new TreeView<CustomItem>();

        /* initialize tree root item */
        TreeItem<CustomItem> root = new TreeItem<CustomItem>(new CustomItem(new Label("Root")));

        /* initialize TreeItem<CustomItem> as container for CustomItem object */
        TreeItem<CustomItem> node = new TreeItem<CustomItem>(new CustomItem(new Label("Node 1"), new Button("Button 1")));

        /* add node to root */
        root.getChildren().add(node);

        /* set tree root */
        tree.setRoot(root);

        /* add items to the layout */
        layout.setCenter(tree);
        return layout;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setWidth(200);
        stage.setHeight(200);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}