package org.example;

import org.example.DAO.RegionDAO;
import org.example.util.enums.ClimatEnum;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegionDAO regionDAO = new RegionDAO();
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n===== MENU REGION =====");
            System.out.println("1. Ajouter une région");
            System.out.println("2. Voir toutes les régions");
            System.out.println("3. Modifier une région");
            System.out.println("4. Supprimer une région");
            System.out.println("5. Voir une région par ID");
            System.out.println("6. Quitter");

            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();

                    System.out.print("Surface : ");
                    double surface = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Climat (PLUIE, SOLEIL, NEIGE) : ");
                    String climatStr = scanner.nextLine();
                    ClimatEnum climat = ClimatEnum.valueOf(climatStr.toUpperCase());

                    Region region = Region.builder()
                            .nom(nom)
                            .surface(surface)
                            .climat(climat)
                            .build();

                    regionDAO.create(region);
                    System.out.println("Région ajoutée");
                }
                case 2 -> {
                    List<Region> regions = regionDAO.findAll();
                    if (regions.isEmpty()) {
                        System.out.println("Aucune région");
                    } else {
                        regions.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("ID de la région à modifier : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Region existing = regionDAO.findById(id);
                    if (existing == null) {
                        System.out.println("Région introuvable.");
                    } else {
                        System.out.print("Nouveau nom (" + existing.getNom() + ") : ");
                        existing.setNom(scanner.nextLine());

                        System.out.print("Nouvelle surface (" + existing.getSurface() + ") : ");
                        existing.setSurface(scanner.nextDouble());
                        scanner.nextLine();

                        System.out.print("Nouveau climat (PLUIE, SOLEIL, NEIGE) : ");
                        existing.setClimat(ClimatEnum.valueOf(scanner.nextLine().toUpperCase()));

                        regionDAO.update(existing);
                        System.out.println("mise à jour resussi !");
                    }
                }
                case 4 -> {
                    System.out.print("ID de la région à supprimer : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    regionDAO.delete(id);
                    System.out.println("Région supprimée.");
                }
                case 5 -> {
                    System.out.print("ID de la région à afficher : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Region region = regionDAO.findById(id);
                    if (region == null) {
                        System.out.println("Région introuvable.");
                    } else {
                        System.out.println(region);
                    }
                }
                case 6 -> {
                    continuer = false;
                    regionDAO.close();
                }
                default -> System.out.println("invalide.");
            }
        }
    }
}
