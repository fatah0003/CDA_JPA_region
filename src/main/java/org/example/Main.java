package org.example;

import org.example.entities.Region;
import org.example.service.RegionService;
import org.example.util.enums.CategoryEnum;
import org.example.util.enums.ClimatEnum;
import org.example.entities.Specie;
import org.example.service.SpecieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegionService regionService = new RegionService();
        SpecieService specieService = new SpecieService();
        boolean run = true;

        while (run) {
            System.out.println("\n===== MENU REGION =====");
            System.out.println("1. Ajouter une région");
            System.out.println("2. Afficher toutes les régions");
            System.out.println("3. Modifier une région");
            System.out.println("4. Supprimer une région");
            System.out.println("5. Rechercher une région par ID");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom de la région : ");
                    String nom = scanner.nextLine();

                    System.out.print("Surface de la région (km²) : ");
                    double surface = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Climat (PLUIE, SOLEIL, NEIGE) : ");
                    String climatStr = scanner.nextLine().toUpperCase();
                    ClimatEnum climat = ClimatEnum.valueOf(climatStr);

                    List<Specie> species = new ArrayList<>();
                    System.out.print("Combien d'espèces voulez-vous ajouter à cette région ? ");
                    int nbSpecies = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < nbSpecies; i++) {
                        System.out.println("Espèce #" + (i + 1));
                        System.out.print("Nom commun : ");
                        String commonName = scanner.nextLine();

                        System.out.print("Nom scientifique : ");
                        String scientificName = scanner.nextLine();

                        System.out.print("Catégorie (BIRD, MAMMAL, INSECT, PLANT, OTHER) : ");
                        String categoryStr = scanner.nextLine().toUpperCase();
                        CategoryEnum category = CategoryEnum.valueOf(categoryStr);

                        Specie specie = Specie.builder()
                                .commonName(commonName)
                                .scientificName(scientificName)
                                .category(category)
                                .build();

                        species.add(specie);
                    }

                    Region newRegion = Region.builder()
                            .nom(nom)
                            .surface(surface)
                            .climat(climat)
                            .species(species)
                            .build();

                    Region saved = regionService.save(newRegion);
                    System.out.println("Région enregistrée avec ID : " + saved.getId());
                    break;


                case 2:
                    List<Region> regions = regionService.get();
                    if (regions.isEmpty()) {
                        System.out.println("Aucune région trouvée.");
                    } else {
                        regions.forEach(System.out::println);
                    }
                    break;

                case 3:
                    System.out.print("ID de la région à modifier : ");
                    long idUpdate = scanner.nextLong();
                    scanner.nextLine();

                    Region regionToUpdate = regionService.get(idUpdate);
                    if (regionToUpdate == null) {
                        System.out.println("Région introuvable.");
                        break;
                    }

                    System.out.print("Nouveau nom (" + regionToUpdate.getNom() + ") : ");
                    regionToUpdate.setNom(scanner.nextLine());

                    System.out.print("Nouvelle surface (" + regionToUpdate.getSurface() + ") : ");
                    regionToUpdate.setSurface(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Nouveau climat (" + regionToUpdate.getClimat() + ") : ");
                    regionToUpdate.setClimat(ClimatEnum.valueOf(scanner.nextLine().toUpperCase()));

                    regionService.update(regionToUpdate, idUpdate);
                    System.out.println("Région mise à jour !");
                    break;

                case 4:
                    System.out.print("ID de la région à supprimer : ");
                    long idDelete = scanner.nextLong();
                    scanner.nextLine();
                    boolean deleted = regionService.delete(idDelete);
                    if (deleted) {
                        System.out.println("Région supprimée !");
                    } else {
                        System.out.println("Échec de la suppression.");
                    }
                    break;

                case 5:
                    System.out.print("ID à rechercher : ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    Region found = regionService.get(id);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Région non trouvée.");
                    }
                    break;

                case 0:
                    run = false;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
