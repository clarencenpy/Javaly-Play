package engine;


import engine.util.ExecuteUtility;


import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 *
 * @author ASUS
 */
public class Compiler {
    private static final String JAVA_EXTENSION  = ".java";
    private static final String CLASS_EXTENSION  = ".class";
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH_SEPARATOR = System.getProperty("path.separator");

    private String[] classPath;
    private String workingDir; //the relative working dir
    private String code;
    private String className;

    private Compiler(){
        //Do not instantiate without any code
    }

    public Compiler(String code){
         this(code, "Test");
    }

    public Compiler(String code, String className){
        this(code, className, null);
    }

    public Compiler(String code, String className, String[] classPath){
        this(code,className, classPath,"");
    }

    public Compiler(String code, String className, String[] classPath, String workingDir){
        this.code = code;
        this.className = className;
        this.classPath = classPath;
        this.workingDir = workingDir + getCompilerIdentifier() + FILE_SEPARATOR ;
    }

    //main process
    public String compileAndRun(){
        File javaFile = stringToJavaFile(code, className);
        String output = compileFile(javaFile, null); //TODO: Integrate classpaths into the compilation step

        //if nothing in output i.e. nothing in std.err; compilation successful; executejavaclass
        if(output.equals("")){
            output = executeJavaClass();
        }

        clean(javaFile.getParentFile());
        return output;
    }

    //compile from existing source to be kept
    public static String compileFromFile(File javaFile){
        String output = compileFile(javaFile, null);
        return output;
    }

    private void clean(File f){
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                if (!c.delete()) {
                    c.deleteOnExit();
                }
            }
        }
        if (!f.delete()){
            f.deleteOnExit();
        }
    }

    private static  String compileFile(File javaFile, String[] classpath){

        //TODO: separate java directory from class directory

        CharArrayWriter  output = new CharArrayWriter(); //stores the output of compilation

        String [] options = new String [2];

        options[0] = ("-classpath");
        String classPaths = "";
        if (classpath != null) {
            for (int i = 0; i < classpath.length; i++) {
                classPaths += classpath[i];
                if (i != classpath.length - 1) {
                    classPaths += PATH_SEPARATOR;
                }
            }
        }
        options[1] = classPaths;

        JavaCompiler compiler =
                ToolProvider.getSystemJavaCompiler(); //must set PATH to jdk and NOT jre for this to return a compiler
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits1 =
                fileManager.getJavaFileObjects(javaFile); //javaFile can be replaced by a list of java files

        compiler.getTask(output, fileManager, null,  Arrays.asList(options), null, compilationUnits1).call();

        try{
            fileManager.close();
            output.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return output.toString();
    }
    private String executeJavaClass(){
        //TODO: iterate through classpaths and add it to the commands
        List<String> command = Arrays.asList("java", "-cp", workingDir,  className);
        return ExecuteUtility.executeCommand(command);
     }

    private File stringToJavaFile (String code, String className){
        new File(workingDir).mkdirs();
        try (FileWriter fw =   new FileWriter(workingDir + className + JAVA_EXTENSION)) {
            fw.write(code);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new File(workingDir  + className + JAVA_EXTENSION);
    }

    private String getCompilerIdentifier(){
        return Integer.toHexString(hashCode());
    }

}
