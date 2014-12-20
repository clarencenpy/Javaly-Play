package com.javaly.engine.java;


import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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

    private String classPath = "" ;
    private String code = "";
    private String className = "";

    private Compiler(){
        //Do not instantiate without any code
    }

    public Compiler(String code){
         this(code, "Test");
    }

    public Compiler(String code, String className){
        this.code = code;
        this.className = className;
    }

    //main process
    public String compileAndRun(){
        File javaFile = stringToJavaFile(code, className);
        String output = compileFile(javaFile); //side effect:  sets the classPath

        if(output.equals("")){
            output = executeJavaClass();
        }

        clean();
        return output;
    }

    private boolean clean(){
        File f = new File (className + JAVA_EXTENSION);
        boolean a = f.delete();

        f = new File (className + CLASS_EXTENSION);
        boolean b = f.delete();

        return a & b;
    }

    private  String compileFile(File javaFile){
        CharArrayWriter  output = new CharArrayWriter(); //stores the output of compilation
        JavaCompiler compiler =
                ToolProvider.getSystemJavaCompiler(); //must set PATH to jdk and NOT jre for this to return a compiler
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits1 =
                fileManager.getJavaFileObjects(javaFile); //javaFile can be replaced by a list of java files
        for(JavaFileObject j : compilationUnits1){
            try{
                String fullPath = new File(j.toUri()).getCanonicalPath();
                classPath = fullPath.substring(0, fullPath.lastIndexOf(FILE_SEPARATOR));
                break;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        compiler.getTask(output, fileManager, null, null, null, compilationUnits1).call();

        try{
            fileManager.close();
            output.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return output.toString();
    }
    private String executeJavaClass(){
        List<String> command = Arrays.asList("java", "-cp", classPath + FILE_SEPARATOR, className);
        return executeCommand(command);
     }

    private static String executeCommand(List<String> command){
        StringBuffer output = new StringBuffer();
        Process p;
        Timer processTimer = new Timer();
        ProcessKiller pk;

        try {
            p = new ProcessBuilder(command).start();
            //kills the thread if it runs for more than 5s (infinite loop)
            pk = new ProcessKiller(p);
            processTimer.schedule(pk ,5000);

            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            if(pk.getHasKilled()) {
                output.insert(0, "ERROR: INFINITE LOOP \n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString().substring(0, output.length() - 1); //remove \n at the end;
    }

    private static File stringToJavaFile (String code, String className){
        try{
            FileWriter fw = new FileWriter(className + JAVA_EXTENSION);
            fw.write(code);
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return new File(className + JAVA_EXTENSION);
    }
    private static class ProcessKiller extends TimerTask{
        Process p;
        boolean hasKilled = false;

        ProcessKiller(Process p){
            this.p = p;
        }

        @Override
        public void run(){
            p.destroy();
            hasKilled = true;
        }

        public boolean getHasKilled(){
            return hasKilled;
        }
    }
}
