import  { useEffect,useState } from "react";
import {
    Button,
    Dialog,
    Card,
    CardBody,
    CardFooter,
    Typography,
    Input,
    Checkbox,
} from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PropTypes from "prop-types";

export function SignInForm({isOpen}) {
    const [open, setOpen] = useState(isOpen);

    useEffect(() => {
        setOpen(isOpen);
    }, [isOpen]);

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <Dialog
            size="xs"
            open={open}
            handler={handleClose}
            className="bg-transparent shadow-none"
        >
            <Card className="mx-auto w-full max-w-[24rem]" >
                <CardBody className="flex flex-col gap-4">
                    <div className="flex items-center justify-between" >
                        <Typography variant="h4" color="blue-gray" >
                            Sign In
                        </Typography>
                        <div className="hover:bg-gray-100 rounded-lg items-center flex justify-center p-2">
                            <FontAwesomeIcon
                                icon="xmark"
                                className="hover:cursor-pointer  h-6 w-6 "
                                onClick={handleClose}
                            />
                        </div>
                    </div>
                    <Typography
                        className="mb-1 font-normal"
                        variant="paragraph"
                        color="gray"

                    >
                        Enter your email and password to Sign In.
                    </Typography>
                    <Typography className="-mb-2" variant="h6">
                        Your Email
                    </Typography>
                    <Input label="Email"/>
                    <Typography className="-mb-2" variant="h6">
                        Your Password
                    </Typography>
                    <Input label="Password" />

                </CardBody>
                <CardFooter className="pt-0">
                    <Button variant="gradient" fullWidth>
                        Sign In
                    </Button>
                    <Typography variant="small" className="mt-4 flex justify-center">
                        Don&apos;t have an account?
                        <Typography
                            as="a"
                            href="#signup"
                            variant="small"
                            color="blue-gray"
                            className="ml-1 font-bold"
                        >
                            Sign up
                        </Typography>
                    </Typography>
                </CardFooter>
            </Card>
        </Dialog>
    );
}
SignInForm.propTypes = {
    isOpen: PropTypes.bool.isRequired,
};