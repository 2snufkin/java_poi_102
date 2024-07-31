import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataClass {

    public static List<Container> createContainersWithBoites() {
        List<Container> containers = new ArrayList<>();

        // Create the first container
        Container container1 = new Container("Container1");
        container1.setDescription("This is the first container");

        // Main Enceintes
        Enceinte c2EnceinteV0 = new Enceinte("1st Level Enceinte C1 I", "SCZ STEMI", generateRandomColor(), 3);
        Enceinte c2EnceinteVi = new Enceinte("1st Level Enceinte C2 II", "Enceinte CZ STEMI", null, 1);
        Enceinte c3EnceinteVi = new Enceinte("1st Level Enceinte C3 III", "Enceinte Boites HD centres extérieurs DEM", generateRandomColor(), 2);

        // SubEnceintes for Enceinte C2 VI (SCZ STEMI)
        Enceinte r1Sub = new Enceinte("Enceinte R1", "()", generateRandomColor(), 1);
        Enceinte t1Sub = new Enceinte("Enceinte T1", "(SCZ1-15)", null, 5);
        Enceinte t2Sub = new Enceinte("Enceinte T2", "(ST+70-88)", generateRandomColor(), 4);
        Enceinte t3Sub = new Enceinte("Enceinte T3", "(ST+89-107)", generateRandomColor(), 3);
        Enceinte t4Sub = new Enceinte("Enceinte T4", "(ST+108-130 EPIpaires BIOMAL)", null, 2);

        // SubEnceintes for Enceinte C3 VI (Boites HD centres extérieurs DEM)
        Enceinte r1SubDem = new Enceinte("Enceinte R1", "(Boites HD centres extérieurs)", generateRandomColor(), 1);
        Enceinte r2Sub = new Enceinte("Enceinte R2", "(Boites HD centres extérieurs)", generateRandomColor(), 2);
        Enceinte r3Sub = new Enceinte("Enceinte R3", "(Boites HD centres extérieurs)", null, 3);

        // Adding Boites to SubEnceintes
        String[][] boitesData = {
                {"Boite ST+70 ()", "Boite ST+89 ()", "Boite ST+108 ()", "Boite NANTES1 ()", "Boite POITIERS (envoi 2)", "Boite STRASBOURG (Envoi 2)", "Boite DEM76 ()", "Boite DEM98 ()"},
                {"Boite BIOCOVID2 (ST)", "Boite SCZ2 ()", "Boite ST+71 ()", "Boite ST+90 ()", "Boite ST+111 ()", "Boite BORDEAUX1 ()", "Boite CLEMONT (envoi 2)", "Boite BESANCON (Envoi 2)", "Boite DEM77 ()", "Boite DEM101 ()"},
        };

        for (String[] boites : boitesData) {
            t1Sub.addBoite(new Boite(boites[1], boites[1], generateRandomColor(), 1));
            t2Sub.addBoite(new Boite(boites[2], boites[2], generateRandomColor(), 2));
            t3Sub.addBoite(new Boite(boites[3], boites[3], generateRandomColor(), 4));
            t4Sub.addBoite(new Boite(boites[4], boites[4], generateRandomColor(), 11));
            r1SubDem.addBoite(new Boite(boites[5], boites[5], generateRandomColor(), 10));
            r2Sub.addBoite(new Boite(boites[6], boites[6], generateRandomColor(), 25));
            r3Sub.addBoite(new Boite(boites[7], boites[7], generateRandomColor(), 3));
        }

        // Adding subEnceintes to the main Enceintes
        c2EnceinteV0.addSubEnceinte(r1Sub);

        c2EnceinteVi.addSubEnceinte(t1Sub);
        c2EnceinteVi.addSubEnceinte(t2Sub);
        c2EnceinteVi.addSubEnceinte(t3Sub);
        c2EnceinteVi.addSubEnceinte(t4Sub);

        c3EnceinteVi.addSubEnceinte(r1SubDem);
        c3EnceinteVi.addSubEnceinte(r2Sub);
        c3EnceinteVi.addSubEnceinte(r3Sub);

        // Adding main Enceintes to the container
        container1.addEnceinte(c2EnceinteV0);
        container1.addEnceinte(c2EnceinteVi);
        container1.addEnceinte(c3EnceinteVi);

        // Add the first container to the list
        containers.add(container1);

        // Create the second container with fewer boites
        Container container2 = new Container("Container2");
        container2.setDescription("This is the second container");

        Enceinte c2EnceinteVi_2 = new Enceinte("Enceinte C2 VI (SCZ STEMI)", "Enceinte C2 VI (SCZ STEMI)", "color1", 1);
        Enceinte c3EnceinteVi_2 = new Enceinte("Enceinte C3 VI (Boites HD centres extérieurs DEM)", "Enceinte C3 VI (Boites HD centres extérieurs DEM)", "color2", 2);

        // Reuse subEnceintes and Boites data for the second container
        for (String[] boites : boitesData) {
            r1Sub.addBoite(new Boite(boites[0], boites[0], generateRandomColor(), 1));
            t1Sub.addBoite(new Boite(boites[1], boites[1], generateRandomColor(), 2));
            t2Sub.addBoite(new Boite(boites[2], boites[2], generateRandomColor(), 3));
            t3Sub.addBoite(new Boite(boites[3], boites[3], generateRandomColor(), 4));
            t4Sub.addBoite(new Boite(boites[4], boites[4], generateRandomColor(), 5));
            r1SubDem.addBoite(new Boite(boites[5], boites[5], generateRandomColor(), 6));
            r2Sub.addBoite(new Boite(boites[6], boites[6], generateRandomColor(), 7));
            r3Sub.addBoite(new Boite(boites[7], boites[7], generateRandomColor(), 8));
        }

        c2EnceinteVi_2.addSubEnceinte(r1Sub);
        c2EnceinteVi_2.addSubEnceinte(t1Sub);
        c2EnceinteVi_2.addSubEnceinte(t2Sub);
        c2EnceinteVi_2.addSubEnceinte(t3Sub);
        c2EnceinteVi_2.addSubEnceinte(t4Sub);

        c3EnceinteVi_2.addSubEnceinte(r1SubDem);
        c3EnceinteVi_2.addSubEnceinte(r2Sub);
        c3EnceinteVi_2.addSubEnceinte(r3Sub);

        container2.addEnceinte(c2EnceinteVi_2);
        container2.addEnceinte(c3EnceinteVi_2);

        containers.add(container2);

        return containers;
    }

    private static String generateRandomColor() {
        Random random = new Random();
        StringBuilder color = new StringBuilder("#");
        while (color.length() < 7) {
            color.append(Integer.toHexString(random.nextInt(16)));
        }
        return color.toString().toUpperCase();
    }

    public static Container createContainerWithNoBoite() {
        Container container = new Container("NBT -80 / Congél VI");
        container.setDescription("TSU 400 V60");
        List<Enceinte> enceintes = new ArrayList<>();

        // Create and add C2 containers
        Enceinte c2BioCovid = new Enceinte("C2", "II (BIOCOVID)", "#0000FF", 1);
        c2BioCovid.addSubEnceinte(new Enceinte("R1", "", null, 1));
        enceintes.add(c2BioCovid);

        Enceinte c2SCZSTEMI = new Enceinte("C2", "VI (SCZ STEMI)", null, 2);
        c2SCZSTEMI.addSubEnceinte(new Enceinte("T1", "SCZ1-15", "#ZZZZZZ", 1)); // Invalid hex code
        Enceinte enceinteT2 = new Enceinte("T2", "ST+70-88", null, 2);
        enceinteT2.addSubEnceinte(new Enceinte("T3", "ST+89-107", "#00FF00", 1));
        c2SCZSTEMI.addSubEnceinte(enceinteT2);
        c2SCZSTEMI.addSubEnceinte(new Enceinte("T5", "ST+89-107", "#00FF00", 3));
        c2SCZSTEMI.addSubEnceinte(new Enceinte("T4", "ST+108-130 EPIpaires BIOMAL", null, 4));
        enceintes.add(c2SCZSTEMI);

        // Create and add C3 containers
        Enceinte c3BoitesHD = new Enceinte("C3", "VI (Boites HD centres extérieurs DEM)", null, 3);
        c3BoitesHD.addSubEnceinte(new Enceinte("R1", "Boites HD centres extérieurs", "#00FF00", 1));
        c3BoitesHD.addSubEnceinte(new Enceinte("R2", "Boites HD centres extérieurs", "#00FF00", 2));
        c3BoitesHD.addSubEnceinte(new Enceinte("R3", "Boites HD centres extérieurs", null, 3));
        c3BoitesHD.addSubEnceinte(new Enceinte("T4", "DEM76-96", null, 4));
        c3BoitesHD.addSubEnceinte(new Enceinte("T5", "DEM98-134", null, 5));
        enceintes.add(c3BoitesHD);

        // Create and add C4 containers
        Enceinte c4MYANPDSEP = new Enceinte("C4", "VI (MYA NPD SEP PNP)", null, 4);
        c4MYANPDSEP.addSubEnceinte(new Enceinte("T1", "MYA1-6 + NPD1-9 boites 9x9", "#FFFF00", 11));
        c4MYANPDSEP.addSubEnceinte(new Enceinte("T2", "NPD10-XXX + MYA7-XXX boites 12x8", "#FFFF00", 2));
        c4MYANPDSEP.addSubEnceinte(new Enceinte("T3", "PNP1-5 + SEP249-280", null, 3));
        c4MYANPDSEP.addSubEnceinte(new Enceinte("T4", "PNP", null, 1));
        enceintes.add(c4MYANPDSEP);
        container.setEnceintes(enceintes);

        return container;
    }

    public static List<Container> createContainersWithNoBoite() {
        List<Container> containers = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Container container = new Container("NBT -80 / Congél VI" + i);
            container.setDescription("TSU 400 V60" + i);
            List<Enceinte> enceintes = new ArrayList<>();

            // Create and add C2 containers
            Enceinte c2BioCovid = new Enceinte("C2", "II (BIOCOVID)", "#0000FF", 1);
            c2BioCovid.addSubEnceinte(new Enceinte("R1", "", null, 1));
            enceintes.add(c2BioCovid);

            Enceinte c2SCZSTEMI = new Enceinte("C2", "VI (SCZ STEMI)", null, 2);
            c2SCZSTEMI.addSubEnceinte(new Enceinte("T1", "SCZ1-15", "#ZZZZZZ", 1)); // Invalid hex code
            Enceinte enceinteT2 = new Enceinte("T2", "ST+70-88", null, 2);
            enceinteT2.addSubEnceinte(new Enceinte("T3", "ST+89-107", "#00FF00", 1));
            c2SCZSTEMI.addSubEnceinte(enceinteT2);
            c2SCZSTEMI.addSubEnceinte(new Enceinte("T5", "ST+89-107", "#00FF00", 3));
            c2SCZSTEMI.addSubEnceinte(new Enceinte("T4", "ST+108-130 EPIpaires BIOMAL", null, 4));
            enceintes.add(c2SCZSTEMI);

            // Create and add C3 containers
            Enceinte c3BoitesHD = new Enceinte("C3", "VI (Boites HD centres extérieurs DEM)", null, 3);
            c3BoitesHD.addSubEnceinte(new Enceinte("R1", "Boites HD centres extérieurs", "#00FF00", 1));
            c3BoitesHD.addSubEnceinte(new Enceinte("R2", "Boites HD centres extérieurs", "#00FF00", 2));
            c3BoitesHD.addSubEnceinte(new Enceinte("R3", "Boites HD centres extérieurs", null, 3));
            c3BoitesHD.addSubEnceinte(new Enceinte("T4", "DEM76-96", null, 4));
            c3BoitesHD.addSubEnceinte(new Enceinte("T5", "DEM98-134", null, 5));
            enceintes.add(c3BoitesHD);

            // Create and add C4 containers
            Enceinte c4MYANPDSEP = new Enceinte("C4", "VI (MYA NPD SEP PNP)", null, 4);
            c4MYANPDSEP.addSubEnceinte(new Enceinte("T1", "MYA1-6 + NPD1-9 boites 9x9", "#FFFF00", 5));
            c4MYANPDSEP.addSubEnceinte(new Enceinte("T2", "NPD10-XXX + MYA7-XXX boites 12x8", "#FFFF00", 2));
            c4MYANPDSEP.addSubEnceinte(new Enceinte("T3", "PNP1-5 + SEP249-280", null, 3));
            c4MYANPDSEP.addSubEnceinte(new Enceinte("T4", "PNP", null, 1));
            enceintes.add(c4MYANPDSEP);

            container.setEnceintes(enceintes);
            containers.add(container);
        }

        return containers;
    }
}
