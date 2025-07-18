package esprit.tn.ms_compte.Controllers;

import esprit.tn.ms_compte.Dto.CompteDto;
import esprit.tn.ms_compte.Services.ICompteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;



@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
@Slf4j
public class CompteRestController {

    private final ICompteService compteService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompteDto add(@RequestBody CompteDto compteDto){
        return compteService.add(compteDto);
    }

    @PatchMapping("{id}")
    public CompteDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return compteService.update(id,fields);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete( @PathVariable String id){
        return compteService.delete(id);
    }


    @GetMapping
    public Page<CompteDto> getComptes(@RequestParam(required = true) int pageNbr,
                                    @RequestParam(required = true) int pageSize){

        return compteService.getComptes(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public CompteDto getCompte(@PathVariable String id){
        return compteService.getCompte(id);
    }

}