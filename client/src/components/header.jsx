import React from "react";
import { strings, colors } from "../common/Strings";
import {
  Button,
  List,
  ListItem,
  Popover,
  PopoverContent,
  PopoverHandler,
} from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function Header() {
  return (
    <div
      className="flex justify-between items-center px-2 "
      style={{ backgroundColor: colors.primary }} // Apply dynamic color here
    >
      {/* Logo */}
      <div className="p-2 rounded-full ">
        <img className="h-10" src={strings.logoImage} alt="Logo" />
      </div>
      {/* Header Navigation */}
      <div>
        <ul className="flex space-x-4 font-semibold text-gray-800">
          <li className="hover:font-bold">Home</li>
          <li className="hover:font-bold">Services</li>
          <li className="hover:font-bold">About</li>
          <li className="hover:font-bold">Contact</li>
        </ul>
      </div>
      {/* Buttons */}
      <div className="inline-flex gap-2 items-center">
        <FontAwesomeIcon
          className="border-2 p-2 rounded-full border-gray-800 hover:bg-gray-800 hover:text-gray-100"
          icon="bell"
        />
        <Popover placement="bottom-start">
          <PopoverHandler>
            <a className=" p-1 border-2 rounded-full border-gray-800 hover:bg-gray-800 hover:text-gray-100">
              <FontAwesomeIcon className="h-4 w-6" icon="user" />
            </a>
          </PopoverHandler>
          <PopoverContent className="rounded-3xl">
            <div>
              <ul className="p-2">
                <li className="hover:font-semibold text-gray-800 pb-2 ">
                  Profile
                </li>
                <li className="hover:font-semibold text-gray-800">
                  Sign In / Create Account
                </li>
              </ul>
            </div>
          </PopoverContent>
        </Popover>
      </div>
    </div>
  );
}
