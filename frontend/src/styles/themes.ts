import {createTheme} from "@mui/material";

export const cardTheme = createTheme({
    components: {
        MuiTextField: {
            defaultProps: {
                multiline: true,
                placeholder: 'Please enter a word...',
            },
            styleOverrides: {
                root: {
                    padding: '5px'
                },
            },
        },
        MuiFab: {
            defaultProps: {
                size: 'large',
            },
            styleOverrides: {
                root: {
                    marginLeft: '20px',
                },
            },
        },
        MuiContainer: {
            styleOverrides: {
                root: {
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                },
            },
        },
        MuiButton: {
            styleOverrides: {
                root: {
                    display: "flex",
                    justifyContent: "right",
                    padding: "10px",
                },
            },
        },
    },
})