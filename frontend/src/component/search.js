import * as React from 'react';
import Chip from '@mui/material/Chip';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import {Container} from '@mui/system';

export default function Tags() {
    return (
        < Container>
            < form>
                < Stack
                    spacing={3}
                    sx={
                        {
                            width: 500
                        }
                    }>

                    <
                        Autocomplete
                        multiple
                        id="tags-outlined"
                        options={actors}
                        getOptionLabel={(option)
                            =>
                            option.name
                        }
                        filterSelectedOptions
                        renderInput={(params)
                            =>
                            (
                                < TextField
                                    {...
                                        params
                                    }
                                    label="Related People"
                                    placeholder="Search by: Related People"
                                    / >
                            )
                            }
                            />
                            < Autocomplete
                            multiple
                            id = "tags-filled"
                            freeSolo

                            renderTags = {(value
                            :
                            string[], getTagProps
                            ) =>
                            value.map((option: string, index: number) => (
                            < Chip
                            variant = "outlined"
                            label = {option}
                            {...
                                getTagProps({index})
                            }
                            />
                            ))
                        }
                            renderInput = {(params)
                            =>
                            (
                            < TextField
                            {...
                                params
                            }
                            variant = "filled"
                            label = "Search by: All fields"
                            placeholder = "Enter a keyword"
                            / >
                            )
                            }
                            />

                            < /Stack>
                            < /form>
                            < /Container>
                            )
                            ;
                            }
                            const actors = [
                            {name: 'Joyce Tyldesley'}
                            ];
