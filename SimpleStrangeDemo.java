// Import core classes from the Strange quantum computing library
import org.redfx.strange.*;
// Import gate classes (like X, Hadamard, etc.)
import org.redfx.strange.gate.*;
// Import a simple local quantum execution environment
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
// Import utility for array handling
import java.util.Arrays;

public class SimpleStrangeDemo {

    public static void main(String[] args) {
        // Create a quantum program with 2 qubits
        Program p = new Program(2);

        // Create an X gate (NOT gate) to flip the 0th qubit
        Gate xGate1 = new X(0);
        // Create a step and add the X gate to it
        Step step1 = new Step();
        step1.addGate(xGate1);
        // Add the step to the program
        p.addStep(step1);

        // Create a Hadamard gate for the 0th qubit to put it into superposition
        Gate hGate2 = new Hadamard(0);
        // Create another X gate to flip the 1st qubit
        Gate xGate2 = new X(1);
        // Create a second step and add both gates to it
        Step step2 = new Step();
        step2.addGates(hGate2, xGate2);
        // Add the second step to the program
        p.addStep(step2);

        // Create a simple quantum execution environment
        SimpleQuantumExecutionEnvironment sqee = new SimpleQuantumExecutionEnvironment();
        // Run the quantum program
        Result res = sqee.runProgram(p);

        // Retrieve the final state of the qubits
        Qubit[] qubits = res.getQubits();
        // Print the probability of measuring 1 and the actual measured value for each qubit
        Arrays.asList(qubits).forEach(q ->
                System.out.println("qubit with probability on 1 = " +
                        q.getProbability() + ", measured it gives " + q.measure()));
    }
}

