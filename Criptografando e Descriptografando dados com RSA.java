import java.io.File;
  import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.ObjectInputStream;
  import java.io.ObjectOutputStream;
  import java.security.KeyPair;
  import java.security.KeyPairGenerator;
  import java.security.PrivateKey;
  import java.security.PublicKey;
  import javax.crypto.Cipher;
   
   
  public class EncriptaDecriptaRSA {
   
    public static final String ALGORITHM = "RSA";
   
    /**
     * Local da chave privada no sistema de arquivos.
     */
    public static final String PATH_CHAVE_PRIVADA = "C:/keys/private.key";
   
    /**
     * Local da chave pública no sistema de arquivos.
     */
    public static final String PATH_CHAVE_PUBLICA = "C:/keys/public.key";
   
    /**
     * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
     * Armazena o conjunto de chaves nos arquivos private.key e public.key
     */
    public static void geraChave() {
      try {
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);
        final KeyPair key = keyGen.generateKeyPair();
   
        File chavePrivadaFile = new File(PATH_CHAVE_PRIVADA);
        File chavePublicaFile = new File(PATH_CHAVE_PUBLICA);
   
        // Cria os arquivos para armazenar a chave Privada e a chave Publica
        if (chavePrivadaFile.getParentFile() != null) {
          chavePrivadaFile.getParentFile().mkdirs();
        }
        
        chavePrivadaFile.createNewFile();
   
        if (chavePublicaFile.getParentFile() != null) {
          chavePublicaFile.getParentFile().mkdirs();
        }
        
        chavePublicaFile.createNewFile();
   
        // Salva a Chave Pública no arquivo
        ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
            new FileOutputStream(chavePublicaFile));
        chavePublicaOS.writeObject(key.getPublic());
        chavePublicaOS.close();
   
        // Salva a Chave Privada no arquivo
        ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
            new FileOutputStream(chavePrivadaFile));
        chavePrivadaOS.writeObject(key.getPrivate());
        chavePrivadaOS.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
   
    }
   
    /**
     * Verifica se o par de chaves Pública e Privada já foram geradas.
     */
    public static boolean verificaSeExisteChavesNoSO() {
   
      File chavePrivada = new File(PATH_CHAVE_PRIVADA);
      File chavePublica = new File(PATH_CHAVE_PUBLICA);
   
      if (chavePrivada.exists() && chavePublica.exists()) {
        return true;
      }
      
      return false;
    }
   
    /**
     * Criptografa o texto puro usando chave pública.
     */
    public static byte[] criptografa(String texto, PublicKey chave) {
      byte[] cipherText = null;
      
      try {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Criptografa o texto puro usando a chave Púlica
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        cipherText = cipher.doFinal(texto.getBytes());
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      return cipherText;
    }
   
    /**
     * Decriptografa o texto puro usando chave privada.
     */
    public static String decriptografa(byte[] texto, PrivateKey chave) {
      byte[] dectyptedText = null;
      
      try {
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        // Decriptografa o texto puro usando a chave Privada
        cipher.init(Cipher.DECRYPT_MODE, chave);
        dectyptedText = cipher.doFinal(texto);
   
      } catch (Exception ex) {
        ex.printStackTrace();
      }
   
      return new String(dectyptedText);
    }
   
    /**
     * Testa o Algoritmo
     */
    public static void main(String[] args) {
   
      try {
   
        // Verifica se já existe um par de chaves, caso contrário gera-se as chaves..
        if (!verificaSeExisteChavesNoSO()) {
         // Método responsável por gerar um par de chaves usando o algoritmo RSA e
         // armazena as chaves nos seus respectivos arquivos.
          geraChave();
        }
   
        final String msgOriginal = "Exemplo de mensagem";
        ObjectInputStream inputStream = null;
   
        // Criptografa a Mensagem usando a Chave Pública
        inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA));
        final PublicKey chavePublica = (PublicKey) inputStream.readObject();
        final byte[] textoCriptografado = criptografa(msgOriginal, chavePublica);
   
        // Decriptografa a Mensagem usando a Chave Pirvada
        inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));
        final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
        final String textoPuro = decriptografa(textoCriptografado, chavePrivada);
   
        // Imprime o texto original, o texto criptografado e 
        // o texto descriptografado.
        System.out.println("Mensagem Original: " + msgOriginal);
        System.out.println("Mensagem Criptografada: " +textoCriptografado.toString());
        System.out.println("Mensagem Decriptografada: " + textoPuro);
   
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

//=======================================================================================

  //Se executarmos o código acima teremos como resultado:

  //Mensagem Original: Exemplo de mensagem
  //Mensagem Criptografada: [B@9ee4e7
  //Mensagem Decriptografada: Exemplo de mensagem
//=======================================================================================
  
//No código acima utilizamos KeyPairGenerator para criar nossa chave. Segue abaixo o trecho de código:

// final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
// keyGen.initialize(1024);
// final KeyPair key = keyGen.generateKeyPair();

//Primeiramente criamos uma instância de KeyPairGenerator para geração das chaves do RSA e após isso inicializamos o gerador informando o tamanho em bits do módulo. Normalmente o tamanho desse módulo é de 1024 bits ou 2048 bits por questões de desempenho. Logo após chamamos o método genKeyPair() que retornará um objeto do tipo KeyPair contendo as chaves. Por fim, chamamos getPublic() e getPrivate() para recuperarmos a chave pública e a chave privada.

//Geradas as chaves já podemos armazená-las no disco para uso posterior e criptografar e descriptografar mensagens.