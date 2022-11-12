package com.ravalparth.pmc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {

    private final Resource file;

    private final String fileName;

    private final long length;

}
