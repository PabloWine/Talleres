public interface SistemaEnvios {

        /***
         * Function used to register new localization or when staring the data read from
         * the txt file.
         * 
         * @param localion name
         */
        public void ingresarLocalizacion(String nombre);

        /**
         * enter the client obtained from the txt file.
         * 
         * @param rut          String containing client's rut
         * @param nombre       String containing customer name
         * @param apellido     String containing client's last name
         * @param saldo        String containing customer balance
         * @param ciudadOrigen String containing the city of origin
         */
        public void ingresarCliente(String rut, String nombre, String apellido, double saldo, String ciudadOrigen);

        /**
         * Function used to register a new document into the system
         * 
         * @param codigo          String containing the shipping code
         * @param rutRemitente    String containing client's rut
         * @param rutDestinatario String containing client's rut
         * @param pesoGramos      double containing shipping weight in grams
         * @param grosorMM        double containing thickness in millimeters of shipment
         * @return
         */
        public boolean ingresarDocumento(String codigo, String rutRemitente, String rutDestinatario,
                        double pesoGramos, double grosorMM);

        /**
         * Function used to register a new order in the system
         * 
         * @param codigo          String containing the shipping code
         * @param rutRemitente    String containing client's rut
         * @param rutDestinatario String containing client's rut
         * @param pesoGramos      double containing shipping weigth in grams
         * @param doubleLargoCM   double contains the length in centimeters
         * @param anchoCM         double contains the broad in centimeters
         * @return
         */
        public boolean ingresarEncomienda(String codigo, String rutRemitente, String rutDestinatario,
                        double pesoGramos, double doubleLargoCM, double anchoCM, double profundidadCM);

        /**
         * Function used to register a new suitcase in the system
         * 
         * @param codigo          String containing the shipping code
         * @param rutRemitente    String containing client's rut
         * @param rutDestinatario String containing client's rut
         * @param material        String containing a bag material
         * @param peso            double contains shipping wieigth in grams
         * @return
         */
        public boolean ingresarValija(String codigo, String rutRemitente, String rutDestinatario, String material,
                        double peso);

        /**
         * Function used to log into the system
         * 
         * @param rut        string containing client's rut
         * @param contraseña in case ob being an administrator it is necessary
         * @return
         */
        public String inicioSesion(String rut, String contraseña);

        /**
         * Function used to register the customer in the system
         * 
         * @param rut          String containing the client's rut
         * @param nombre       String containing customer name
         * @param apellido     String containing client's last name
         * @param ciudadOrigen String containing the city of origin
         * @return
         */
        public boolean registro(String rut, String nombre, String apellido, String ciudadOrigen);

        public boolean realizarEntrega(String rutDestinatario, String tipo, String rutRemitente, double total);

        public boolean recargarSaldo(String rutCliente, double recarga);

        public String verMisEntrefgas(String rutCliente);

        public String entregasPorTipo();

        public String entregaPorLocalizacion();

        public String entregasPorCliente();

        public String registroDeGanancias();

        public String obtenerDatosClientes();

}
