import javax.swing.*;

public class OrderFormGUI {
    public static void main(String[] args) {
        /** DO NOT CHANGE VALUES BELOW **/
        boolean hoodieInStock = true;
        boolean tshirtInStock = false;
        boolean longsleeveInStock = true;
        String item = "";
        int quantity = 0;
        String name = "";
        /** DO NOT CHANGE VALUES ABOVE **/


        //TODO: display error GUI if item selected is out of stock
        boolean looper = true;
        String[] options = {"Hoodie", "T-shirt", "Long sleeve"};
        item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                JOptionPane.PLAIN_MESSAGE, null, options, null);
        while (looper) {
            if (item.equals("Hoodie") && !hoodieInStock) {
                JOptionPane.showMessageDialog(null, "Out of Stock",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
                item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                        JOptionPane.PLAIN_MESSAGE, null, options, null);
            } else if (item.equals("T-shirt") && !tshirtInStock) {
                JOptionPane.showMessageDialog(null, "Out of Stock",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
                item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                        JOptionPane.PLAIN_MESSAGE, null, options, null);
            } else if (item.equals("Long sleeve") && !longsleeveInStock) {
                JOptionPane.showMessageDialog(null, "Out of Stock",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
                item = (String) JOptionPane.showInputDialog(null, "Select item style ", "Order Form",
                        JOptionPane.PLAIN_MESSAGE, null, options, null);
            } else {
                looper = false;
            }
        }

        boolean exit = false;
        //TODO: display error GUI if input is not an int or if input is less than 1
        quantity = quantityExceptionCatcher();
        if(quantity < 1) {
            do {
                JOptionPane.showMessageDialog(null, "Enter a number greater than 0",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
                quantity = quantityExceptionCatcher();
            } while (quantity <= 0);
        }
        //TODO: display error GUI if input does not include a space
        name = JOptionPane.showInputDialog(null, "Enter your Name", "Order Form",
                JOptionPane.QUESTION_MESSAGE);
        boolean exitLoop = false;
        if (!name.contains(" ")) {
            while (!exitLoop) {
                JOptionPane.showMessageDialog(null, "Enter first and last name",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
                name = JOptionPane.showInputDialog(null, "Enter your Name", "Order Form",
                        JOptionPane.QUESTION_MESSAGE);
                if (name.contains(" ")) {
                    exitLoop = true;
                }
            }
        }

        /** Order Confirmation Message **/
        String resultMessage = "Name: " + name + "\nItem: " + item + "\nQuantity: " + quantity;
        JOptionPane.showMessageDialog(null, resultMessage, "Order Confirmation", JOptionPane.INFORMATION_MESSAGE);

        //TODO: loop through order form again if YES
        int dialogButton = JOptionPane.showConfirmDialog(null, "Would you like to place another order?", "Order Form", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            main(new String[]{});
        }


    }

    public static int quantityExceptionCatcher() {
        int quantity = 0;
        do {
            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter quantity", "Order Form",
                        JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter an integer",
                        "Order Form", JOptionPane.ERROR_MESSAGE);
            }
        } while (quantity <= 0);
        return quantity;
    }
}
