import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import { Container } from "@mui/system";
import { Button, Paper } from "@mui/material";
import axios from "axios";

export default function SearchTextField() {
  const paperStyle = { padding: "50px 20px", width: 600, margin: "20px auto" };
  const [keyword, setKeyword] = React.useState([]);
  const [actor, setActor] = React.useState("");
  const [place, setPlace] = React.useState("");
  const [type, setType] = React.useState("");

  const actors = [{ name: "Joyce Tyldesley" }];

  const types = [
    {
      name: "charcoal",
    },
    {
      name: "stopper",
    },
    {
      name: "containers",
    },
    {
      name: "cups",
    },
    {
      name: "goblets",
    },
    {
      name: "bracelts",
    },
    {
      name: "torques",
    },
    {
      name: "crystal andesite",
    },
    {
      name: "terracotta ",
    },
    {
      name: "weapons",
    },
    {
      name: "brooches",
    },
    {
      name: "plaque",
    },
    {
      name: "bottles",
    },
    {
      name: "spade",
    },
    {
      name: "palstaves",
    },
    {
      name: "shovel",
    },
    {
      name: "scrapers",
    },
    {
      name: "assemblages {archaeological groupings}",
    },
    {
      name: "bowls",
    },
    {
      name: "hammerstones",
    },
    {
      name: "projectile points",
    },
    {
      name: "flint implements",
    },
    {
      name: "rings",
    },
    {
      name: "pecking crushing tools",
    },
    {
      name: "pendants",
    },
    {
      name: "knifes",
    },
  ];
  const handleClick = (e) => {
    axios({
      url: "http://localhost:8989/search",
      params: {
        keyword: keyword,
        actor: actor,
        place: place,
        type: type,
      },
    })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => console.error(err));
  };
  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1> Explore the collection </h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": {
              m: 1,
            },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="outlined-basic"
            label="Search by: All fields"
            variant="outlined"
            fullWidth
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />
          <Autocomplete
            multiple
            id="tags-outlined"
            fullWidth
            options={actors}
            getOptionLabel={(option) => option.name}
            filterSelectedOptions
            inputValue={actor}
            onInputChange={(event, newInputValue) => {
              setActor(newInputValue);
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                label="Related People"
                placeholder="Search by: Related People"
              />
            )}
          />
          <TextField
            id="outlined-basic"
            label="Related Place"
            variant="outlined"
            fullWidth
            value={place}
            onChange={(e) => setPlace(e.target.value)}
          />
          <Autocomplete
            multiple
            id="tags-outlined"
            options={types}
            filterSelectedOptions
            fullWidth
            inputValue={type}
            onInputChange={(event, newInputValue) => {
              setType(newInputValue);
            }}
            getOptionLabel={(option) => option.name}
            renderInput={(params) => (
              <TextField
                {...params}
                label="Object Type"
                placeholder="Classification"
              />
            )}
          />

          <Button variant="contained" onClick={handleClick}>
            {" "}
            Search
          </Button>
        </Box>
      </Paper>
    </Container>
  );
}
