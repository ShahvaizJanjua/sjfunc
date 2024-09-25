package com.example.fn;

public class HelloFunction {

    public String handleRequest(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        //Map data = objectMapper.convertValue(event.getData().get(), Map.class);
        //Map additionalDetails = objectMapper.convertValue(data.get("additionalDetails"), Map.class);
        System.out.println("Building ObjectURL, OBJ Name: "+input);
        String objectURL = "https://frofufwuvghu.objectstorage.uk-london-1.oci.customer-oci.com/p/w7dj07NLfA0cJcKaIGl7oJwLiZaZmjkOzjaFDLeDfQKzauu9muot5HqVj_NXLYd0/n/frofufwuvghu/b/SJBucket/o/"
        +input;

        /*try (BufferedInputStream in = new BufferedInputStream(new URL(objectURL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(input)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }   
            } catch (IOException e) {
             // handle exception
        }*/
        System.out.println("Ready to D/L Object from OSS");
        InputStream in = new URL(objectURL).openStream();
        Files.copy(in, Paths.get(input), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Obj downloaded");
    }

}