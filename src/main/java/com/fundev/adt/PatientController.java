package com.fundev.adt;

import com.fundev.adt.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getById(@PathVariable(value = "id") Long patientId)
            throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping("/patients")
    public Patient create(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> update(@PathVariable(value = "id") Long patientId, @RequestBody Patient patientData) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patient.setFirstName(patientData.getFirstName());
        patient.setLastName(patientData.getLastName());
        patient.setBirthDate(patientData.getBirthDate());
        final Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/patients/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long patientId)
            throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
