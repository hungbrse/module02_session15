package module02_sesion15.ra.run;

import module02_sesion15.ra.entity.Categories;
import module02_sesion15.ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
   public  static   Categories[] arrCategories = new Categories[100];
    public static Product[] arrProduct = new Product[100];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int categoryCount = 0;
        int productCount = 0;
        int choice;

        do {
            System.out.println("-----------SHOP MENU-----------");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau khi nhập int

            switch (choice) {
                case 1:
                    manageCategories(scanner, arrCategories, arrProduct);
                    break;
                case 2:
                    manageProducts(scanner, arrProduct, arrCategories);
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        } while (choice != 3);

        scanner.close();
    }

    // Quản lý danh mục
    private static void manageCategories(Scanner scanner, Categories[] arrCategories, Product[] arrProduct) {
        int choice;
        do {
            System.out.println("---------------CATEGORIES MENU------------------");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau khi nhập int

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng danh mục cần thêm: ");
                    int numCategories = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numCategories; i++) {
                        Categories newCategory = new Categories();
                        newCategory.inputData(scanner, arrCategories, i);
                        arrCategories[i] = newCategory;
                    }
                    break;
                case 2:
                    for (Categories cat : arrCategories) {
                        if (cat != null) {
                            cat.displayData();
                        }
                    }
                    break;
                case 3:
                    for (Categories cat : arrCategories) {
                        cat.displayData();
                    }
                    System.out.print("Nhập mã danh mục cần cập nhật: ");
                    int catalogId = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = false;
                    for (Categories cat : arrCategories) {
                        if (cat != null && cat.getCatalogId() == catalogId) {
                            found = true;
                            cat.inputData(scanner, arrCategories, catalogId);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Mã danh mục không tồn tại.");
                    }
                    break;
                case 4:
                    for (Categories cat : arrCategories) {
                        cat.displayData();
                    }
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int deleteCatalogId = scanner.nextInt();
                    scanner.nextLine();
                    boolean deleteFound = false;
                    boolean hasProducts = false;
                    for (Product prod : arrProduct) {
                        if (prod != null && prod.getCatalogId() == deleteCatalogId) {
                            hasProducts = true;
                            break;
                        }
                    }
                    if (!hasProducts) {
                        for (int i = 0; i < arrCategories.length; i++) {
                            if (arrCategories[i] != null && arrCategories[i].getCatalogId() == deleteCatalogId) {
                                arrCategories[i] = null;
                                deleteFound = true;
                                System.out.println("Xóa danh mục thành công.");
                                break;
                            }
                        }
                    } else {
                        System.out.println("Không thể xóa danh mục vì đang có sản phẩm thuộc danh mục này.");
                    }
                    if (!deleteFound && !hasProducts) {
                        System.out.println("Mã danh mục không tồn tại.");
                    }
                    break;
                case 5:
                    for (Categories cat : arrCategories) {
                        cat.displayData();
                    }
                    System.out.print("Nhập mã danh mục cần cập nhật trạng thái: ");
                    int updateCatalogId = scanner.nextInt();
                    scanner.nextLine();
                    boolean updateFound = false;
                    for (Categories cat : arrCategories) {
                        if (cat != null && cat.getCatalogId() == updateCatalogId) {
                            cat.setCatalogStatus(!cat.isCatalogStatus());
                            System.out.println("Cập nhật trạng thái thành công.");
                            updateFound = true;
                            break;
                        }
                    }
                    if (!updateFound) {
                        System.out.println("Mã danh mục không tồn tại.");
                    }
                    break;
                case 6:
                    System.out.println("Thoát về menu chính.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        } while (choice != 6);
    }

    // Quản lý sản phẩm
    private static void manageProducts(Scanner scanner, Product[] arrProduct, Categories[] arrCategories) {
        int choice;
        do {
            System.out.println("---------------PRODUCT MANAGEMENT----------------");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau khi nhập int

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm cần thêm: ");
                    int numProducts = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numProducts; i++) {
                        Product newProduct = new Product();
                        newProduct.inputData(scanner, arrProduct, i, arrCategories);
                        arrProduct[i] = newProduct;
                    }
                    break;
                case 2:
                    for (Product prod : arrProduct) {
                        if (prod != null) {
                            prod.displayData();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < arrProduct.length - 1; i++) {
                        for (int j = i + 1; j < arrProduct.length; j++) {
                            if (arrProduct[i] != null && arrProduct[j] != null && arrProduct[i].getPrice() > arrProduct[j].getPrice()) {
                                Product temp = arrProduct[i];
                                arrProduct[i] = arrProduct[j];
                                arrProduct[j] = temp;
                            }
                        }
                    }
                    System.out.println("Sắp xếp các sản phẩm theo giá thành công.");
                    break;

                case 4:
                    for (Product prod : arrProduct) {
                        prod.displayData();
                    }
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                    String updateProductId = scanner.nextLine();
                    boolean productFound = false;
                    for (Product prod : arrProduct) {
                        if (prod != null && prod.getProductId().equals(updateProductId)) {
                            prod.inputData(scanner, arrProduct, 0, arrCategories);
                            productFound = true;
                            System.out.println("Cập nhật thông tin sản phẩm thành công.");
                            break;
                        }
                    }
                    if (!productFound) {
                        System.out.println("Mã sản phẩm không tồn tại.");
                    }
                    break;

                case 5:
                    for (Product prod : arrProduct) {
                        prod.displayData();
                    }
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String deleteProductId = scanner.nextLine();
                    boolean deleteProductFound = false;
                    for (int i = 0; i < arrProduct.length; i++) {
                        if (arrProduct[i] != null && arrProduct[i].getProductId().equals(deleteProductId)) {
                            arrProduct[i] = null;
                            deleteProductFound = true;
                            System.out.println("Xóa sản phẩm thành công.");
                            break;
                        }
                    }
                    if (!deleteProductFound) {
                        System.out.println("Mã sản phẩm không tồn tại.");
                    }
                    break;

                case 6:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    String searchName = scanner.nextLine().toLowerCase();
                    boolean foundProductByName = false;
                    for (Product prod : arrProduct) {
                        if (prod != null && prod.getProductName().toLowerCase().contains(searchName)) {
                            prod.displayData();
                            foundProductByName = true;
                        }
                    }
                    if (!foundProductByName) {
                        System.out.println("Không tìm thấy sản phẩm với tên đã nhập.");
                    }
                    break;

                case 7:
                    System.out.print("Nhập giá thấp nhất: ");
                    float minPrice = scanner.nextFloat();
                    System.out.print("Nhập giá cao nhất: ");
                    float maxPrice = scanner.nextFloat();
                    scanner.nextLine();
                    boolean foundProductByPrice = false;
                    for (Product prod : arrProduct) {
                        if (prod != null && prod.getPrice() >= minPrice && prod.getPrice() <= maxPrice) {
                            prod.displayData();
                            foundProductByPrice = true;
                        }
                    }
                    if (!foundProductByPrice) {
                        System.out.println("Không tìm thấy sản phẩm trong khoảng giá đã nhập.");
                    }
                    break;

                case 8:
                    System.out.println("Thoát về menu chính.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        } while (choice != 8);
    }
}